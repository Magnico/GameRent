package com.uninorte.sophos.controllers;

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

import com.uninorte.sophos.models.Productor;
import com.uninorte.sophos.repository.ProductorRepository;


@RestController
public class ProductorController {
	
	@Autowired
	private ProductorRepository repository;
	
	
	public ProductorController() {
		super();
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(path = "/productor/get/all")
	public List<Productor> getAllProductor(){
		return repository.findAll();
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(path = "/productor/get/{productor_id}")
	public Productor getProductor(@PathVariable int productor_id){		
		return repository.findById(productor_id).get();
        
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping(path = "/productor/create")
	public Productor createProductor(@RequestBody Productor newProductor){
		return repository.save(newProductor);
	}
	
	@CrossOrigin(origins = "*")
	@DeleteMapping(path = "/productor/delete")
	public List<Productor> deleteProductor(@RequestParam Integer productor_id){
		repository.deleteById(productor_id);
		return repository.findAll();
	}
}
