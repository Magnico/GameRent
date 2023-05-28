package com.uninorte.sophos.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.uninorte.sophos.model.*;

@FeignClient(name = "rent-management-service")
public interface RentManagementServiceProxy {
	
	@CrossOrigin(origins = "*")
	@GetMapping(path = "/renta/get/all")
	public List<Renta> getAllRent();

	@CrossOrigin(origins = "*")
	@GetMapping(path = "/renta/get/{renta_id}")
	public ResponseEntity<Object> getRent(@PathVariable int renta_id);

	@CrossOrigin(origins = "*")
	@PostMapping(path = "/renta/create")
	public ResponseEntity<Object> createRent(@RequestBody Renta newRenta);

	@CrossOrigin(origins = "*")
	@DeleteMapping(path = "/renta/delete")
	public List<Renta> deleteRent(@RequestParam Integer renta_id);

	@CrossOrigin(origins = "*")
	@GetMapping(path = "/renta/frecuent/client/")
	public Cliente frecuentClient();

	@CrossOrigin(origins = "*")
	@GetMapping(path = "/renta/frecuent/juego/")
	public Juego frecuentJuego();

	@CrossOrigin(origins = "*")
	@PatchMapping(path = "/renta/return/{renta_id}")
	public ResponseEntity<Object> updateState(@PathVariable int renta_id);
	
	@CrossOrigin(origins = "*")
	@GetMapping(path = "/renta/day/sells")
	public List<Renta> getAllRentDay();
}
