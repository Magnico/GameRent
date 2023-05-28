package com.uninorte.sophos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.uninorte.sophos.models.Cliente;
import com.uninorte.sophos.models.Juego;
import com.uninorte.sophos.models.ProofOfPurchase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;

@RestController
public class RentaController {

	@Autowired
	private RentaRepository repository;

	public RentaController() {
		super();
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
	@GetMapping(path = "/renta/get/all")
	public List<Renta> getAllRent() {
		return repository.findAll();
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
	@GetMapping(path = "/renta/get/{renta_id}")
	public ResponseEntity<Object> getRent(@PathVariable int renta_id) {
		try {
			Renta renta = repository.findById(renta_id).get();
			return ResponseEntity.ok(renta);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la renta asociada con esa ID.");
		}

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
	@PostMapping(path = "/renta/create")
	public ResponseEntity<Object> createRent(@RequestBody Renta newRenta) {
		try {
			Renta renta = repository.saveAndFlush(newRenta);
			return ResponseEntity.status(HttpStatus.CREATED).body(new ProofOfPurchase(renta));
		} catch (Exception e) {
			String errorMessage = "No se pueden rentar más titulos hasta que se realicen las devoluciones faltantes.";
			return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
		}
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
	@DeleteMapping(path = "/renta/delete")
	public List<Renta> deleteRent(@RequestParam Integer renta_id) {
		repository.deleteById(renta_id);
		return repository.findAll();
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
	@GetMapping(path = "/renta/frecuent/client/")
	public Cliente frecuentClient() {
		return this.mostFrequentElement(Cliente.class);
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
	@GetMapping(path = "/renta/frecuent/juego/")
	public Juego frecuentJuego() {
		return this.mostFrequentElement(Juego.class);
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
	@PatchMapping(path = "/renta/return/{renta_id}")
	public ResponseEntity<Object> updateState(@PathVariable int renta_id) {
		try {
			Renta renta = repository.getReferenceById(renta_id);

			String estado = renta.getEstado();
			switch (estado) {
			case "VENCIDO":
			case "ACTIVA":
				String updatedEstado = (estado.equals("VENCIDA")) ? "FINALIZADA_V" : "FINALIZADA_A";
				renta.setEstado(updatedEstado);
				repository.save(renta);
				return ResponseEntity.ok("Retorno exitoso");

			default:
				return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Ya se encuentra regresó esta renta");
			}
		} catch (EntityNotFoundException e) {
			String errorMessage = "No se encontró renta registrada bajo esa ID";
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
		}
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
	@GetMapping(path = "/renta/day/sells")
	public List<Renta> getAllRentDay(){
		return repository.findAllByCreationDate(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
	}

	public <T> T mostFrequentElement(Class<T> returnType) {
	    List<Renta> rentas = repository.findAll();
	    HashMap<T, Integer> elements = new HashMap<>();
	    int max = 0;
	    T frequent = null;
	    for (Renta renta : rentas) {
	        if (returnType == Juego.class) {
	            List<Juego> juegos = renta.getJuegos();
	            for (Juego juego : juegos) {
	                int value = elements.getOrDefault(returnType.cast(juego), 0) + 1;
	                elements.put(returnType.cast(juego), value);
	                if (value > max) {
	                    max = value;
	                    frequent = returnType.cast(juego);
	                }
	            }
	        } else if (returnType == Cliente.class) {
	            Cliente cliente = renta.getCliente();
	            int value = elements.getOrDefault(returnType.cast(cliente), 0) + 1;
	            elements.put(returnType.cast(cliente), value);
	            if (value > max) {
	                max = value;
	                frequent = returnType.cast(cliente);
	            }
	        } else {
	            throw new IllegalArgumentException("Invalid class type");
	        }
	    }
	    return frequent;
	}

}
