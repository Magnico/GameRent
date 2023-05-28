package com.uninorte.sophos;

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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class ClienteController {
	
	@Autowired
	private ClienteRepository repository;
	
	
	public ClienteController() {
		super();
	}
	
	@CrossOrigin(origins = "*")
	@Operation(summary="Recuperar lista de clientes")
	@ApiResponses(value= {
			@ApiResponse(
					responseCode="200", 
					description="Lista de cliente obtenida.",
					content = @Content(array = @ArraySchema(schema = @Schema(implementation = Cliente.class)))
					)
			})
	@GetMapping(path = "/user/get/all")
	public List<Cliente> getAllUser(){
		return repository.findAll();
	}
	
	@CrossOrigin(origins = "*")
	@Operation(summary = "Recuperar cliente")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200", 
					description = "Cliente obtenido.", 
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))
					),
			@ApiResponse(
					responseCode = "404", 
					description = "No se encuentra el cliente.",
					content = @Content(mediaType = "text/plain", schema = @Schema(type ="string"))
					) 
			})
	@GetMapping(path = "/user/get/{user_id}")
	public ResponseEntity<Object> getUser(@PathVariable int user_id){		
		try {
			Cliente cliente = repository.findById(user_id).get();
			return ResponseEntity.ok(cliente);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la renta asociada con esa ID.");
		}
	}
	
	@CrossOrigin(origins = "*")
	@Operation(summary = "Registrar nuevo cliente")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "201", 
					description = "Cliente registrado con exito.",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))
					)
			})
	@PostMapping(path = "/user/create")
	public ResponseEntity<Cliente> createUser(@RequestBody Cliente newUser){
		Cliente cliente = repository.saveAndFlush(newUser);
		return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
	}
	
	@CrossOrigin(origins = "*")
	@Operation(summary = "Eliminar cliente")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Cliente eliminado.",
					content = @Content(array = @ArraySchema(schema = @Schema(implementation = Cliente.class)))
					) 
			})
	@DeleteMapping(path = "/user/delete")
	public List<Cliente> deleteUser(@RequestParam Integer user_id){
		repository.deleteById(user_id);
		return repository.findAll();
	}

}
