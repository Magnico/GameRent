package com.uninorte.sophos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
public class JuegoController {
	
	@Autowired
	private JuegoRepository repository;
	
	
	public JuegoController() {
		super();
	}
	
	@CrossOrigin(origins = "*")
	@Operation(summary="Recuperar lista de juegos")
	@ApiResponses(value= {
			@ApiResponse(
					responseCode="200", 
					description="Lista de juegos obtenida.",
					content = @Content(array = @ArraySchema(schema = @Schema(implementation = Juego.class)))
					)
			})
	@GetMapping(path = "/juego/get/all")
	public List<Juego> getJuegos(
            @RequestParam(required = false) String fecha_pub,
            @RequestParam(required = false) Integer directorId,
            @RequestParam(required = false) Integer personajeId,
            @RequestParam(required = false) Integer productorId) {

        return repository.filterJuegos(fecha_pub, directorId, personajeId, productorId);
    }
	
	@CrossOrigin(origins = "*")
	@Operation(summary="Recuperar juego")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200", 
					description = "Juego obtenido.", 
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = Juego.class))
					),
			@ApiResponse(
					responseCode = "404", 
					description = "No se encuentra el juego.",
					content = @Content(mediaType = "text/plain", schema = @Schema(type ="string"))
					) 
			})
	@GetMapping(path = "/juego/get/{juego_id}")
	public ResponseEntity<Object> getJuego(@PathVariable int juego_id){		
		try {
			Juego juego = repository.findById(juego_id).get();
			return ResponseEntity.ok(juego);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el productor asociado con esa ID.");
		}
	}
	
	@CrossOrigin(origins = "*")
	@Operation(summary = "Registrar nuevo juego")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "201", 
					description = "Juego registrado con exito.",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = Juego.class))
					)
			})
	@PostMapping(path = "/juego/create")
	public Juego createJuego(@RequestBody Juego newJuego){
		return repository.save(newJuego);
	}
	
	@CrossOrigin(origins = "*")
	@Operation(summary = "Eliminar juego")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Juego eliminado.",
					content = @Content(array = @ArraySchema(schema = @Schema(implementation = Juego.class)))
					) 
			})
	@DeleteMapping(path = "/juego/delete")
	public List<Juego> deleteJuego(@RequestParam Integer juego_id){
		repository.deleteById(juego_id);
		return repository.findAll();
	}
	
	@CrossOrigin(origins = "*")
	@Operation(summary = "Estadistica del juego menos rentado por rango de edad de clientes (10 años)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Información retornada")
    })
	@GetMapping("/juego/info")
	public ResponseEntity<Object> getLeastFrequentJuegoIdByAgeRange() {
		List<Object[]> results = repository.getViewInformation();
		
	    List<Map<String, Object>> response = new ArrayList<>();

	    for (Object[] result : results) {
	    	Map<String, Object> juego = new HashMap<>();
	    	juego.put("juego_id",result[0]);
	    	juego.put("fecha_pub",result[2]);
	    	juego.put("nombre",result[3]);
	    	juego.put("plataforma",result[4]);
	    	juego.put("rentado",result[5]);
	    	juego.put("director_id",result[6]);
	    	
	        Map<String, Object> entry = new HashMap<>();
	        entry.put("age_range", result[1]);
	        entry.put("least_frequent_juego_id", juego);

	        response.add(entry);
	    }

	    return ResponseEntity.ok(response);
	}
}
