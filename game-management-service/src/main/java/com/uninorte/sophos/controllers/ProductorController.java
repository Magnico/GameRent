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

import com.uninorte.sophos.models.Productor;
import com.uninorte.sophos.repository.ProductorRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
public class ProductorController {
	
	@Autowired
	private ProductorRepository repository;
	
	
	public ProductorController() {
		super();
	}
	
	@CrossOrigin(origins = "*")
	@Operation(summary="Recuperar lista de productores")
	@ApiResponses(value= {
			@ApiResponse(
					responseCode="200", 
					description="Lista de productores obtenida.",
					content = @Content(array = @ArraySchema(schema = @Schema(implementation = Productor.class)))
					)
			})
	@GetMapping(path = "/productor/get/all")
	public List<Productor> getAllProductor(){
		return repository.findAll();
	}
	
	@CrossOrigin(origins = "*")
	@Operation(summary = "Recuperar productor")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200", 
					description = "Productor obtenido.", 
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = Productor.class))
					),
			@ApiResponse(
					responseCode = "404", 
					description = "No se encuentra el productor.",
					content = @Content(mediaType = "text/plain", schema = @Schema(type ="string"))
					) 
			})
	@GetMapping(path = "/productor/get/{productor_id}")
	public ResponseEntity<Object> getProductor(@PathVariable int productor_id){		
		try {
			Productor productor = repository.findById(productor_id).get();
			return ResponseEntity.ok(productor);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el productor asociado con esa ID.");
		}
	}
	
	@CrossOrigin(origins = "*")
	@Operation(summary = "Registrar nuevo productor")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "201", 
					description = "Productor registrado con exito.",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = Productor.class))
					)
			})
	@PostMapping(path = "/productor/create")
	public Productor createProductor(@RequestBody Productor newProductor){
		return repository.save(newProductor);
	}
	
	@CrossOrigin(origins = "*")
	@Operation(summary = "Eliminar productor")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Productor eliminado.",
					content = @Content(array = @ArraySchema(schema = @Schema(implementation = Productor.class)))
					) 
			})
	@DeleteMapping(path = "/productor/delete")
	public List<Productor> deleteProductor(@RequestParam Integer productor_id){
		repository.deleteById(productor_id);
		return repository.findAll();
	}
}
