package com.uninorte.sophos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RentaController {
	
	@Autowired
	private RentaRepository repository;
	
	
	public RentaController() {
		super();
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(path = "/renta/get/all")
	public List<Renta> getAllRent(){
		return repository.findAll();
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(path = "/renta/get/{renta_id}")
	public Renta getRent(@PathVariable int renta_id){		
		return repository.findById(renta_id).get();
        
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping(path = "/renta/create")
	public Renta createRent(@RequestBody Renta newRenta){
		return repository.save(newRenta);
	}
	
	@CrossOrigin(origins = "*")
	@DeleteMapping(path = "/renta/delete")
	public List<Renta> deleteUser(@RequestParam Integer renta_id){
		repository.deleteById(renta_id);
		return repository.findAll();
	} 

}
