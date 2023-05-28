package com.uninorte.sophos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uninorte.sophos.model.*;
import com.uninorte.sophos.proxy.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@CrossOrigin(origins="*")
public class IntegrationController {
	
	@Autowired
	private ClientManagementServiceProxy userManagementServiceProxy;
	
	@Autowired
	private RentManagementServiceProxy rentManagementServiceProxy;

	@Autowired
	private GameManagementServiceProxy gameManagementServiceProxy;
	
	@CrossOrigin(origins = "*")
	@Operation(summary="Recuperar lista de clientes")
	@ApiResponses(value= {
			@ApiResponse(
					responseCode="200", 
					description="Lista de cliente obtenida.",
					content = @Content(array = @ArraySchema(schema = @Schema(implementation = Cliente.class)))
					)
			})
	@GetMapping(path = "/integration/user/get/all")
	public List<Cliente> getAllUser(){
		return userManagementServiceProxy.getAllUser();
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
	@GetMapping(path = "/integration/user/get/{user_id}")
	public ResponseEntity<Object> getUser(@PathVariable int user_id){
		return userManagementServiceProxy.getUser(user_id);
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
	@PostMapping(path = "/integration/user/create")
	public ResponseEntity<Cliente> createUser(@RequestBody Cliente newUser){
		return userManagementServiceProxy.createUser(newUser);
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
	@DeleteMapping(path = "/integration/user/delete")
	public List<Cliente> deleteUser(@RequestParam Integer user_id){
		return userManagementServiceProxy.deleteUser(user_id);
	}
	
	
	@CrossOrigin(origins = "*")
	@Operation(summary = "Recuperar lista de rentas")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Lista de rentas obtenida.",
					content = @Content(array = @ArraySchema(schema = @Schema(implementation = Renta.class)))
					) 
			})
	@GetMapping(path = "/integration/renta/get/all")
	public List<Renta> getAllRent(){
		return rentManagementServiceProxy.getAllRent();
	}

	@CrossOrigin(origins = "*")
	@Operation(summary = "Recuperar renta")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200", 
					description = "Renta obtenida.", 
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = Renta.class))
					),
			@ApiResponse(
					responseCode = "404", 
					description = "No se encuentra la renta.",
					content = @Content(mediaType = "text/plain", schema = @Schema(type ="string"))
					) 
			})
	@GetMapping(path = "/integration/renta/get/{renta_id}")
	public ResponseEntity<Object> getRent(@PathVariable int renta_id){
		return rentManagementServiceProxy.getRent(renta_id);
	}

	@CrossOrigin(origins = "*")
	@Operation(summary = "Registrar nueva renta")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "201", 
					description = "Renta registrada con exito.",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProofOfPurchase.class))
					),
			@ApiResponse(
					responseCode = "409", 
					description = "No se pueden rentar más titulos hasta que se realicen las devoluciones faltantes.",
					content = @Content(mediaType = "text/plain", schema = @Schema(type ="string"))
					) 
			})
	@PostMapping(path = "/integration/renta/create")
	public ResponseEntity<Object> createRent(@RequestBody Renta newRenta){
		return rentManagementServiceProxy.createRent(newRenta);
	}

	@CrossOrigin(origins = "*")
	@Operation(summary = "Eliminar renta")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Renta eliminada.",
					content = @Content(array = @ArraySchema(schema = @Schema(implementation = Renta.class)))
					) 
			})
	@DeleteMapping(path = "/integration/renta/delete")
	public List<Renta> deleteRent(@RequestParam Integer renta_id){
		return rentManagementServiceProxy.deleteRent(renta_id);
	}

	@CrossOrigin(origins = "*")
	@Operation(summary = "Recuperar cliente más frecuente")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Cliente más frecuente obtenido.",
					content = @Content(array = @ArraySchema(schema = @Schema(implementation = Cliente.class)))					
					) 
			})
	@GetMapping(path = "/integration/renta/frecuent/client/")
	public Cliente frecuentClient(){
		return rentManagementServiceProxy.frecuentClient();
	}

	@CrossOrigin(origins = "*")
	@Operation(summary = "Recuperar juego más rentado")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Juego más rentado obtenido.",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = Juego.class))
					) 
			})
	@GetMapping(path = "/integration/renta/frecuent/juego/")
	public Juego frecuentJuego(){
		return rentManagementServiceProxy.frecuentJuego();
	}

	@CrossOrigin(origins = "*")
	@Operation(summary = "Pagar renta pendiente")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Retorno realizado con exito.",
					content = @Content(mediaType = "text/plain", schema = @Schema(type ="string"))
					),
			@ApiResponse(
					responseCode = "304", 
					description = "Retorno no realizado, renta ya retornada.",
					content = @Content(mediaType = "text/plain", schema = @Schema(type ="string"))
					),
			@ApiResponse(
					responseCode = "404", 
					description = "No se encuentra dicha renta.",
					content = @Content(mediaType = "text/plain", schema = @Schema(type ="string"))
					) 
			})
	@PatchMapping(path = "/integration/renta/return/{renta_id}")
	public ResponseEntity<Object> updateState(@PathVariable int renta_id){
		return rentManagementServiceProxy.updateState(renta_id);
	}
	
	@CrossOrigin(origins = "*")
	@Operation(summary = "Recuperar lista de rentas del día")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Lista de rentas obtenida.",
					content = @Content(array = @ArraySchema(schema = @Schema(implementation = Renta.class)))
					) 
			})
	@GetMapping(path = "/integration/renta/day/sells")
	public List<Renta> getAllRentDay(){
		return rentManagementServiceProxy.getAllRentDay();
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
	@GetMapping(path = "/integration/juego/get/all")
	public List<Juego> getJuegos(
            @RequestParam(required = false) String fecha_pub,
            @RequestParam(required = false) Integer directorId,
            @RequestParam(required = false) Integer personajeId,
            @RequestParam(required = false) Integer productorId){
		return gameManagementServiceProxy.getJuegos(fecha_pub, directorId, personajeId, productorId);
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
	@GetMapping(path = "/integration/juego/get/{juego_id}")
	public ResponseEntity<Object> getJuego(@PathVariable int juego_id){
		return gameManagementServiceProxy.getJuego(juego_id);
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
	@PostMapping(path = "/integration/juego/create")
	public Juego createJuego(@RequestBody Juego newJuego){
		return gameManagementServiceProxy.createJuego(newJuego);
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
	@DeleteMapping(path = "/integration/juego/delete")
	public List<Juego> deleteJuego(@RequestParam Integer juego_id){
		return gameManagementServiceProxy.deleteJuego(juego_id);
	}
	
	@CrossOrigin(origins = "*")
	@Operation(summary = "Estadistica del juego menos rentado por rango de edad de clientes (10 años)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Información retornada")
    })
	@GetMapping(path = "/integration/juego/info")
	public ResponseEntity<Object> getLeastFrequentJuegoIdByAgeRange(){
		return gameManagementServiceProxy.getLeastFrequentJuegoIdByAgeRange();
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
	@GetMapping(path = "/integration/director/get/all")
	public List<Director> getAlldirector(){
		return gameManagementServiceProxy.getAlldirector();
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
	@GetMapping(path = "/integration/director/get/{director_id}")
	public ResponseEntity<Object> getdirector(@PathVariable int director_id){
		return gameManagementServiceProxy.getdirector(director_id);
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
	@PostMapping(path = "/integration/director/create")
	public ResponseEntity<Director> createdirector(@RequestBody Director newDirector){
		return gameManagementServiceProxy.createdirector(newDirector);
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
	@DeleteMapping(path = "/integration/director/delete")
	public List<Director> deletedirector(@RequestParam Integer director_id){
		return gameManagementServiceProxy.deletedirector(director_id);
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
	@GetMapping(path = "/integration/personaje/get/all")
	public List<Personaje> getAllPersonaje(){
		return gameManagementServiceProxy.getAllPersonaje();
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
	@GetMapping(path = "/integration/personaje/get/{personaje_id}")
	public ResponseEntity<Object> getPersonaje(@PathVariable int personaje_id){
		return gameManagementServiceProxy.getPersonaje(personaje_id);
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
	@PostMapping(path = "/integration/personaje/create")
	public ResponseEntity<Personaje> createPersonaje(@RequestBody Personaje newPersonaje){
		return gameManagementServiceProxy.createPersonaje(newPersonaje);
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
	@DeleteMapping(path = "/integration/personaje/delete")
	public List<Personaje> deletePersonaje(@RequestParam Integer personaje_id){
		return gameManagementServiceProxy.deletePersonaje(personaje_id);
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
	@GetMapping(path = "/integration/productor/get/all")
	public List<Productor> getAllProductor(){
		return gameManagementServiceProxy.getAllProductor();
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
	@GetMapping(path = "/integration/productor/get/{productor_id}")
	public ResponseEntity<Object> getProductor(@PathVariable int productor_id){
		return gameManagementServiceProxy.getProductor(productor_id);
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
	@PostMapping(path = "/integration/productor/create")
	public Productor createProductor(@RequestBody Productor newProductor){
		return gameManagementServiceProxy.createProductor(newProductor);
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
	@DeleteMapping(path = "/integration/productor/delete")
	public List<Productor> deleteProductor(@RequestParam Integer productor_id){
		return gameManagementServiceProxy.deleteProductor(productor_id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
