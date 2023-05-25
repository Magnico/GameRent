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
public class JuegoController {
	
	@Autowired
	private JuegoRepository repository;
	
	
	public JuegoController() {
		super();
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(path = "/juego/get/all")
	public List<Juego> getAllJuego(){
		return repository.findAll();
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(path = "/juego/get/{juego_id}")
	public Juego getJuego(@PathVariable int juego_id){		
		return repository.findById(juego_id).get();
        
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping(path = "/juego/create")
	public Juego createJuego(@RequestBody Juego newJuego){
		return repository.save(newJuego);
	}
	
	@CrossOrigin(origins = "*")
	@DeleteMapping(path = "/juego/delete")
	public List<Juego> deleteJuego(@RequestParam Integer juego_id){
		repository.deleteById(juego_id);
		return repository.findAll();
	}
}
