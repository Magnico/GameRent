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

import com.uninorte.sophos.models.Personaje;
import com.uninorte.sophos.repository.PersonajeRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
public class PersonajeController {
	
	@Autowired
	private PersonajeRepository repository;
	
	
	public PersonajeController() {
		super();
	}
	
	@CrossOrigin(origins = "*")
	@Operation(summary="Recuperar lista de personajes")
	@ApiResponses(value= {
			@ApiResponse(
					responseCode="200", 
					description="Lista de personajes obtenida.",
					content = @Content(array = @ArraySchema(schema = @Schema(implementation = Personaje.class)))
					)
			})
	@GetMapping(path = "/personaje/get/all")
	public List<Personaje> getAllPersonaje(){
		return repository.findAll();
	}
	
	@CrossOrigin(origins = "*")
	@Operation(summary = "Recuperar personaje")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200", 
					description = "Personaje obtenido.", 
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = Personaje.class))
					),
			@ApiResponse(
					responseCode = "404", 
					description = "No se encuentra el personaje.",
					content = @Content(mediaType = "text/plain", schema = @Schema(type ="string"))
					) 
			})
	@GetMapping(path = "/personaje/get/{personaje_id}")
	public ResponseEntity<Object> getPersonaje(@PathVariable int personaje_id){		
		try {
			Personaje personaje= repository.findById(personaje_id).get();
			return ResponseEntity.ok(personaje);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el personaje asociado con esa ID.");
		}
	}
	
	@CrossOrigin(origins = "*")
	@Operation(summary = "Registrar nuevo personaje")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "201", 
					description = "Personaje registrado con exito.",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = Personaje.class))
					)
			})
	@PostMapping(path = "/personaje/create")
	public ResponseEntity<Personaje> createPersonaje(@RequestBody Personaje newPersonaje){
		Personaje personaje = repository.saveAndFlush(newPersonaje);
		return ResponseEntity.status(HttpStatus.CREATED).body(personaje);
	}
	
	@CrossOrigin(origins = "*")
	@Operation(summary = "Eliminar personaje")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Personaje eliminado.",
					content = @Content(array = @ArraySchema(schema = @Schema(implementation = Personaje.class)))
					) 
			})
	@DeleteMapping(path = "/personaje/delete")
	public List<Personaje> deletePersonaje(@RequestParam Integer personaje_id){
		repository.deleteById(personaje_id);
		return repository.findAll();
	}

}
