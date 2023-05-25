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

import com.uninorte.sophos.models.Personaje;
import com.uninorte.sophos.repository.PersonajeRepository;


@RestController
public class PersonajeController {
	
	@Autowired
	private PersonajeRepository repository;
	
	
	public PersonajeController() {
		super();
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(path = "/personaje/get/all")
	public List<Personaje> getAllPersonaje(){
		return repository.findAll();
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(path = "/personaje/get/{personaje_id}")
	public Personaje getPersonaje(@PathVariable int personaje_id){		
		return repository.findById(personaje_id).get();
        
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping(path = "/personaje/create")
	public Personaje createPersonaje(@RequestBody Personaje newPersonaje){
		return repository.save(newPersonaje);
	}
	
	@CrossOrigin(origins = "*")
	@DeleteMapping(path = "/personaje/delete")
	public List<Personaje> deletePersonaje(@RequestParam Integer personaje_id){
		repository.deleteById(personaje_id);
		return repository.findAll();
	}

}
