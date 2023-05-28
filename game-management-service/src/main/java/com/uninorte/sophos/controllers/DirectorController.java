package com.uninorte.sophos.controllers;

import java.util.List;
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

import com.uninorte.sophos.models.Director;
import com.uninorte.sophos.repository.DirectorRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
public class DirectorController {
	
	@Autowired
	private DirectorRepository repository;
	
	
	public DirectorController() {
		super();
	}
	
	@CrossOrigin(origins = "*")
	@Operation(summary="Recuperar lista de directores")
	@ApiResponses(value= {
			@ApiResponse(
					responseCode="200", 
					description="Lista de directores obtenida.",
					content = @Content(array = @ArraySchema(schema = @Schema(implementation = Director.class)))
					)
			})
	@GetMapping(path = "/director/get/all")
	public List<Director> getAlldirector(){
		return repository.findAll();
	}
	
	@CrossOrigin(origins = "*")
	@Operation(summary = "Recuperar director")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200", 
					description = "Director obtenido.", 
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = Director.class))
					),
			@ApiResponse(
					responseCode = "404", 
					description = "No se encuentra el director.",
					content = @Content(mediaType = "text/plain", schema = @Schema(type ="string"))
					) 
			})
	@GetMapping(path = "/director/get/{director_id}")
	public ResponseEntity<Object> getdirector(@PathVariable int director_id){		
		try {
			Director director = repository.findById(director_id).get();
			return ResponseEntity.ok(director);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el director asociado con esa ID.");
		}
        
	}
	
	@CrossOrigin(origins = "*")
	@Operation(summary = "Registrar nuevo director")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "201", 
					description = "Director registrado con exito.",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = Director.class))
					)
			})
	@PostMapping(path = "/director/create")
	public ResponseEntity<Director> createdirector(@RequestBody Director newDirector){
		Director director = repository.saveAndFlush(newDirector);
		return ResponseEntity.status(HttpStatus.CREATED).body(director);
	}
	
	@CrossOrigin(origins = "*")
	@Operation(summary = "Eliminar director")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Director eliminado.",
					content = @Content(array = @ArraySchema(schema = @Schema(implementation = Director.class)))
					) 
			})
	@DeleteMapping(path = "/director/delete")
	public List<Director> deletedirector(@RequestParam Integer director_id){
		repository.deleteById(director_id);
		return repository.findAll();
	}
}
