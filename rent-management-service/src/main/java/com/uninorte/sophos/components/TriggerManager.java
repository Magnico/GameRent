package com.uninorte.sophos.components;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class TriggerManager implements ResourceLoaderAware {

	private final JdbcTemplate jdbcTemplate;
	private ResourceLoader resourceLoader;

	@Autowired
	public TriggerManager(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void createTriggers() {
		this.createCheckRentaVencidaTrigger();
		this.createUpdateClienteFieldTrigger();
		this.createUpdateJuegoFieldOnInsertTrigger();
		this.createUpdateJuegoFieldOnUpdateTrigger();
	}

	private void createUpdateClienteFieldTrigger() {
		String triggerSQL = """
				CREATE TRIGGER IF NOT EXISTS update_cliente_field
				AFTER UPDATE ON renta
				FOR EACH ROW
				BEGIN
				    IF NEW.estado = 'VENCIDO' AND OLD.estado != NEW.estado THEN
				        UPDATE cliente SET renta_vencida = 1 WHERE user_id = NEW.cliente_id;
				    ELSEIF NEW.estado IN ('FINALIZADA_A', 'FINALIZADA_V') THEN
				        UPDATE cliente SET renta_vencida = 0 WHERE user_id = NEW.cliente_id;
				    END IF;
				END;
								    		""";

		jdbcTemplate.execute(triggerSQL);
	}

	private void createCheckRentaVencidaTrigger() {
		String triggerSQL = """
				CREATE TRIGGER IF NOT EXISTS check_renta_vencida
				BEFORE INSERT ON renta
				FOR EACH ROW
				BEGIN
				    DECLARE v_renta_vencida TINYINT;
				    SELECT renta_vencida INTO v_renta_vencida FROM cliente WHERE user_id = NEW.cliente_id;
				    IF v_renta_vencida = TRUE THEN
				        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Cannot add renta for cliente with renta_vencida = TRUE';
				    END IF;
				END
				""";

		jdbcTemplate.execute(triggerSQL);
	}

	
	private void createUpdateJuegoFieldOnInsertTrigger() {
		String triggerSQL = """
				CREATE TRIGGER IF NOT EXISTS update_juego_rentado AFTER INSERT ON renta_juego
				FOR EACH ROW
				BEGIN
				    DECLARE renta_status VARCHAR(255);

				    SELECT estado INTO renta_status
				    FROM renta
				    WHERE renta_id = NEW.renta_id;

				    IF (renta_status = 'ACTIVA' OR renta_status = 'VENCIDO') THEN
				        UPDATE juego
				        SET rentado = 1
				        WHERE juego_id = NEW.juego_id;
				    ELSE
				        UPDATE juego
				        SET rentado = 0
				        WHERE juego_id = NEW.juego_id;
				    END IF;
				END
				""";

		jdbcTemplate.execute(triggerSQL);
	}
	
	
	private void createUpdateJuegoFieldOnUpdateTrigger() {
		String triggerSQL = """
				CREATE TRIGGER IF NOT EXISTS renta_estado_trigger AFTER UPDATE ON renta
				FOR EACH ROW
				BEGIN
				    IF (NEW.estado = 'FINALIZADA_A' OR NEW.estado = 'FINALIZADA_V') THEN
				        UPDATE juego
				        INNER JOIN renta_juego ON juego.juego_id = renta_juego.juego_id
				        SET juego.rentado = 0
				        WHERE renta_juego.renta_id = NEW.renta_id;
				    END IF;
				END
				""";

		jdbcTemplate.execute(triggerSQL);
	}
	
	
	public void executePopulateSqlFile() {
		Resource resource = resourceLoader.getResource("file:/app/populate.sql");
	    try (InputStream inputStream = resource.getInputStream()) {
	        byte[] bytes = inputStream.readAllBytes();
	        String populateSQL = new String(bytes, StandardCharsets.ISO_8859_1);

	        String[] sqlStatements = populateSQL.split(";");

	        for (String sqlStatement : sqlStatements) {
	            if (!sqlStatement.trim().isEmpty()) {
	                jdbcTemplate.execute(sqlStatement);
	            }
	        }
	    } catch (IOException e) {
	        // Handle IO exception
	    }
    }

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}
}
