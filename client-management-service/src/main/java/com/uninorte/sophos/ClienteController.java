package com.uninorte.sophos;

import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ClienteController {
	
	@Autowired
	private static Environment environment;
	
	@Autowired
	private ClienteRepository repository;
	
	
	public ClienteController() {
		super();
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(path = "/user/get/all")
	public List<Cliente> getAllUser(@RequestHeader(name = "Authorization",defaultValue = "APP-CODE;UNIXTIMESTAMP;UNIQ-TOKEN") String authorization){
		return repository.findAll();
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(path = "/user/get/{user_id}")
	public Cliente getUser(@RequestHeader(name = "Authorization",defaultValue = "APP-CODE;UNIXTIMESTAMP;UNIQ-TOKEN") String authorization,@PathVariable int user_id){		
		return repository.findById(user_id).get();
        
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping(path = "/user/create")
	public Cliente createUser(@RequestBody Cliente newUser){
		repository.save(newUser);
		return newUser;
	}
	
	@CrossOrigin(origins = "*")
	@DeleteMapping(path = "/user/delete")
	public List<Cliente> deleteUser(@RequestHeader(name = "Authorization",defaultValue = "APP-CODE;UNIXTIMESTAMP;UNIQ-TOKEN") String authorization,@RequestParam Integer user_id){
		repository.deleteById(user_id);
		return repository.findAll();
	} 

	@CrossOrigin(origins = "*")
	@PostMapping(path="/login")
	public String logUser( @RequestBody Map<String, String> requestBody){
		String userIdent = requestBody.get("userIdent");
		String password = requestBody.get("password");
		
		Cliente test = getCliente(userIdent);	
		if (test != null) {
			if(test.getPassword().equals(password)) {
				String Hash = createHash(password, userIdent.toString());
				test = repository.save(test);
				JSONObject response = new JSONObject();
				response.put("token", Hash);
				response.put("user_name", test.getNombre());
				response.put("user_id", test.getUser_id());
				response.put("user_email",test.getEmail());
				return response.toString();
			}
			return null;
		}
		return null;
	}
	
	protected String createHash(@PathVariable String pass, @PathVariable String id) {
		long unix = System.currentTimeMillis() /1000L;
		String clear_text = pass+unix;
		String encrypted_text =  DigestUtils.sha256Hex(clear_text);
		String uncoded = id+";"+encrypted_text+";"+unix;
		String token = Base64.getEncoder().encodeToString(uncoded.getBytes());
		return token;
		
	}

	public Cliente getCliente(String ident) {
		List<Cliente> listofUsers = repository.findAll();
	    for (Cliente user : listofUsers) {
	        if (user.getIdentificacion().equals(ident)) {
	            return user;
	        }
	    }
	    return null; // return null if element not found
	}

}
