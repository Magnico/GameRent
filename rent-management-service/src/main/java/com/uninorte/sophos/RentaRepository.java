package com.uninorte.sophos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface RentaRepository extends JpaRepository<Renta, Integer>{
	
	@Modifying
    @Transactional
    @Query("UPDATE Renta SET estado = 'VENCIDO' WHERE STR_TO_DATE(fecha_ven, '%d/%m/%Y') <= :currentDate")
    void updateEstadoToVencido(LocalDate currentDate);
	
	List<Renta> findAllByCreationDate(String creationDate);
}
