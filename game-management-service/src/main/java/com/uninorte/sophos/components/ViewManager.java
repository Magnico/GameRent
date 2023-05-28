package com.uninorte.sophos.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ViewManager {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public ViewManager(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void createViews() {
		this.createViewJuego();
	}

	private void createViewJuego() {
		String checkIfExistsQuery = "SELECT COUNT(*) FROM information_schema.views WHERE table_schema = 'services' AND table_name = 'least_rented_juego_by_age_range';";
		int count = jdbcTemplate.queryForObject(checkIfExistsQuery, Integer.class);

		if (count == 0) {
			String createViewQuery = """
					CREATE VIEW least_rented_juego_by_age_range AS
					SELECT * FROM (
					SELECT age_range, MIN(juego_id) AS juego_id
					FROM (
						SELECT juego_id, FLOOR(TIMESTAMPDIFF(YEAR,str_to_date(fecha_nac,'%d/%m/%Y'),CURDATE())/10)*10 AS age_range
						FROM renta_juego
						INNER JOIN renta ON renta.renta_id = renta_juego.renta_id
						INNER JOIN cliente ON user_id = cliente_id
						) AS juego_age_range
						GROUP BY age_range
						ORDER BY age_range
					    ) AS info
					NATURAL JOIN juego;
					""";
			jdbcTemplate.execute(createViewQuery);
		}
	}
}
