package edu.lawrence.meetUp.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.lawrence.meetUp.entities.Profile;
import edu.lawrence.meetUp.entities.User;
import edu.lawrence.meetUp.interfaces.dtos.ProfileDTO;
import edu.lawrence.meetUp.interfaces.dtos.RankingDTO;
import edu.lawrence.meetUp.interfaces.dtos.UserDTO;
import edu.lawrence.meetUp.security.JwtService;
import edu.lawrence.meetUp.security.MeetupUserDetails;
import edu.lawrence.meetUp.security.WrongUserException;
import edu.lawrence.meetUp.services.DuplicateException;
import edu.lawrence.meetUp.services.UserService;

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
	public ResponseEntity<String> login(@RequestBody UserDTO user){
		User result = us.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	    if (result == null) {
	    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("/Invalid username or password/");
        }
        String token = jwtService.makeJwt(result.getUserid().toString());
        user.setToken(token);
        return ResponseEntity.ok().body(token);
	}
	
	@PostMapping("/profile/{id}")
	public ResponseEntity<String> saveProfile(/*Authentication authentication*/@PathVariable("id") UUID id,@RequestBody ProfileDTO profile) {
		//MeetupUserDetails details = (MeetupUserDetails) authentication.getPrincipal();	
		profile.setUser(id.toString());
    	try {
    		us.saveProfile(profile);
    	} catch(WrongUserException ex) {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("/Invalid user id/");
    	} catch(DuplicateException ex) {
    		return ResponseEntity.status(HttpStatus.CONFLICT).body("/Duplicate profile/");
    	}
    	return ResponseEntity.status(HttpStatus.CREATED).body("/Profile created/");
    }
	
    @GetMapping("/profile/{id}")
    public ResponseEntity<ProfileDTO> getProfile(/*Authentication authentication*/@PathVariable("id") UUID id) {
    	//MeetupUserDetails details = (MeetupUserDetails) authentication.getPrincipal();
    	//UUID id = UUID.fromString(details.getUsername());
    	Profile result = us.findProfile(id);
    	if(result == null) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    	}
    	ProfileDTO response = new ProfileDTO(result);
    	return ResponseEntity.ok().body(response);
    }
    
    @GetMapping("/profile/sport/{sport}")
    public ResponseEntity<List<ProfileDTO>> getProfileBySport(@PathVariable String sport) {
    	List<Profile> results = us.findProfileBySport(sport);
    	if(results == null) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    	}
    	
    	List<ProfileDTO> response = new ArrayList<ProfileDTO>();
    	for(Profile p : results) {
    		response.add(new ProfileDTO(p));
    	}
    	return ResponseEntity.ok().body(response);
    }
    
	@GetMapping(params = {"lat","long","sport"})
	public ResponseEntity<List<ProfileDTO>> getProfileByLocationAndSport(@RequestParam(value = "long")String longitude,@RequestParam(value = "lat")String latitude,@RequestParam(value = "sport")String sport) {
		List<Profile> results = us.findProfileByLocationAndSport(longitude,latitude,sport);
		if(results == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
		List<ProfileDTO> response = new ArrayList<ProfileDTO>();
		for(Profile p : results) {
			response.add(new ProfileDTO(p));
		}
		return ResponseEntity.ok().body(response);
	}
    
    @PostMapping("/ranking/{id}")
    public ResponseEntity<String> saveRanking(/*Authentication authentication*/@PathVariable("id") UUID id,@RequestBody RankingDTO ranking){
    	//MeetupUserDetails details = (MeetupUserDetails) authentication.getPrincipal();
    	//UUID id = UUID.fromString(details.getUsername());
    	try {
    		us.setRanking(id, ranking);
    	} catch(WrongUserException ex) {
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("/Invalid user id/");
    	} catch(DuplicateException ex) {
    		return ResponseEntity.status(HttpStatus.CONFLICT).body("/Duplicate user/");
    	}
    	return ResponseEntity.status(HttpStatus.CREATED).body("/Ranking set/");
    }
    
	

 //key = userService.save(user);
//user.setUserid(user);
}
