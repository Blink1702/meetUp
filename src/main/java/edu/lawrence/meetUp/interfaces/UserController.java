package edu.lawrence.meetUp.interfaces;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.lawrence.meetUp.entities.User;
import edu.lawrence.meetUp.interfaces.dtos.ProfileDTO;
import edu.lawrence.meetUp.interfaces.dtos.UserDTO;
import edu.lawrence.meetUp.security.JwtService;
import edu.lawrence.meetUp.services.DuplicateException;
import edu.lawrence.meetUp.services.UserService;
import edu.lawrence.meetUp.services.WrongUserException;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {
	
	private UserService us;
	private JwtService jwtService;
	
	public UserController(UserService us, JwtService jwtService) {
		this.us = us;
		this.jwtService = jwtService;
	}
	
	@PostMapping
	public ResponseEntity<UserDTO> save(@RequestBody UserDTO user){
		if(user.getUsername().isBlank() || user.getPassword().isBlank()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user);
		}
		String key;
		try {
			key = us.save(user);
		} catch(DuplicateException ex) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(user);
		}
		String token = jwtService.makeJwt(key);
		user.setToken(token);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserDTO> login(@RequestBody UserDTO user){
		User result = us.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	    if (result == null) {
	    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(user);
        }
        String token = jwtService.makeJwt(result.getUserid().toString());
        user.setToken(token);
        return ResponseEntity.ok().body(user);
	}
	
	@PostMapping("/{id}/profile")
	public ResponseEntity<String> saveProfile(@PathVariable UUID id,@RequestBody ProfileDTO profile) {
    	//UUID id = UUID.fromString(User.getUsername()';);
    	try {
    		us.saveProfile(id,profile);
    	} catch(WrongUserException ex) {
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("/Invalid user id/");
    	} catch(DuplicateException ex) {
    		return ResponseEntity.status(HttpStatus.CONFLICT).body("/Duplicate profile/");
    	}
    	return ResponseEntity.status(HttpStatus.CREATED).body("/Profile created/");
    }
	

 //key = userService.save(user);
//user.setUserid(user);
}
