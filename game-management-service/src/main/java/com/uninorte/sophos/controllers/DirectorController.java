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

import com.uninorte.sophos.models.Director;
import com.uninorte.sophos.repository.DirectorRepository;


@RestController
public class DirectorController {
	
	@Autowired
	private DirectorRepository repository;
	
	
	public DirectorController() {
		super();
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(path = "/director/get/all")
	public List<Director> getAlldirector(){
		return repository.findAll();
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(path = "/director/get/{director_id}")
	public Director getdirector(@PathVariable int director_id){		
		return repository.findById(director_id).get();
        
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping(path = "/director/create")
	public Director createdirector(@RequestBody Director newDirector){
		return repository.save(newDirector);
	}
	
	@CrossOrigin(origins = "*")
	@DeleteMapping(path = "/director/delete")
	public List<Director> deletedirector(@RequestParam Integer director_id){
		repository.deleteById(director_id);
		return repository.findAll();
	}
}
