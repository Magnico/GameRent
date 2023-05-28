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

import com.uninorte.sophos.model.Cliente;

@FeignClient(name = "client-management-service")
public interface ClientManagementServiceProxy {
	

	@CrossOrigin(origins = "*")
	@GetMapping(path = "/user/get/all")
	public List<Cliente> getAllUser();
	
	@CrossOrigin(origins = "*")
	@GetMapping(path = "/user/get/{user_id}")
	public ResponseEntity<Object> getUser(@PathVariable int user_id);
	
	@CrossOrigin(origins = "*")
	@PostMapping(path = "/user/create")
	public ResponseEntity<Cliente> createUser(@RequestBody Cliente newUser);
	
	@CrossOrigin(origins = "*")
	@DeleteMapping(path = "/user/delete")
	public List<Cliente> deleteUser(@RequestParam Integer user_id);
}
