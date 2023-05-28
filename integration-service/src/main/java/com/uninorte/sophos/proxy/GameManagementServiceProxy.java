package com.uninorte.sophos.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.uninorte.sophos.model.*;

@FeignClient(name = "game-management-service")
public interface GameManagementServiceProxy {
	
	@CrossOrigin(origins = "*")
	@GetMapping(path = "/juego/get/all")
	public List<Juego> getJuegos(
            @RequestParam(required = false) String fecha_pub,
            @RequestParam(required = false) Integer directorId,
            @RequestParam(required = false) Integer personajeId,
            @RequestParam(required = false) Integer productorId);
	
	@CrossOrigin(origins = "*")
	@GetMapping(path = "/juego/get/{juego_id}")
	public ResponseEntity<Object> getJuego(@PathVariable int juego_id);
	
	@CrossOrigin(origins = "*")
	@PostMapping(path = "/juego/create")
	public Juego createJuego(@RequestBody Juego newJuego);
	
	@CrossOrigin(origins = "*")
	@DeleteMapping(path = "/juego/delete")
	public List<Juego> deleteJuego(@RequestParam Integer juego_id);
	
	@CrossOrigin(origins = "*")
	@GetMapping("/juego/info")
	public ResponseEntity<Object> getLeastFrequentJuegoIdByAgeRange();
	
	@CrossOrigin(origins = "*")
	@GetMapping(path = "/director/get/all")
	public List<Director> getAlldirector();
	
	@CrossOrigin(origins = "*")
	@GetMapping(path = "/director/get/{director_id}")
	public ResponseEntity<Object> getdirector(@PathVariable int director_id);
	
	@CrossOrigin(origins = "*")
	@PostMapping(path = "/director/create")
	public ResponseEntity<Director> createdirector(@RequestBody Director newDirector);
	
	@CrossOrigin(origins = "*")
	@DeleteMapping(path = "/director/delete")
	public List<Director> deletedirector(@RequestParam Integer director_id);
	
	@CrossOrigin(origins = "*")
	@GetMapping(path = "/personaje/get/all")
	public List<Personaje> getAllPersonaje();
	
	@CrossOrigin(origins = "*")
	@GetMapping(path = "/personaje/get/{personaje_id}")
	public ResponseEntity<Object> getPersonaje(@PathVariable int personaje_id);
	
	@CrossOrigin(origins = "*")
	@PostMapping(path = "/personaje/create")
	public ResponseEntity<Personaje> createPersonaje(@RequestBody Personaje newPersonaje);
	
	@CrossOrigin(origins = "*")
	@DeleteMapping(path = "/personaje/delete")
	public List<Personaje> deletePersonaje(@RequestParam Integer personaje_id);
	
	@CrossOrigin(origins = "*")
	@GetMapping(path = "/productor/get/all")
	public List<Productor> getAllProductor();
	
	@CrossOrigin(origins = "*")
	@GetMapping(path = "/productor/get/{productor_id}")
	public ResponseEntity<Object> getProductor(@PathVariable int productor_id);
	
	@CrossOrigin(origins = "*")
	@PostMapping(path = "/productor/create")
	public Productor createProductor(@RequestBody Productor newProductor);
	
	@CrossOrigin(origins = "*")
	@DeleteMapping(path = "/productor/delete")
	public List<Productor> deleteProductor(@RequestParam Integer productor_id);
}
