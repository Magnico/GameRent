package com.uninorte.sophos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JuegoRepository extends JpaRepository<Juego, Integer> {

	@Query(value = "SELECT * FROM least_rented_juego_by_age_range", nativeQuery = true)
	List<Object[]> getViewInformation();
	
	@Query("SELECT j FROM Juego j WHERE (:fecha_pub IS NULL OR j.fecha_pub = :fecha_pub) " +
            "AND (:directorId IS NULL OR j.director.id = :directorId) " +
            "AND (:personajeId IS NULL OR EXISTS (SELECT pj FROM j.personajes pj WHERE pj.id = :personajeId)) " +
            "AND (:productorId IS NULL OR EXISTS (SELECT pd FROM j.productores pd WHERE pd.id = :productorId))")
    List<Juego> filterJuegos(
            @Param("fecha_pub") String fecha_pub,
            @Param("directorId") Integer directorId,
            @Param("personajeId") Integer personajeId,
            @Param("productorId") Integer productorId);

}

