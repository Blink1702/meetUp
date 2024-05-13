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

import edu.lawrence.meetUp.interfaces.dtos.EventDTO;
import edu.lawrence.meetUp.services.EventService;

@RestController
@RequestMapping("/events")
@CrossOrigin(origins = "*")
public class EventController {
	private EventService es;
	
	public EventController(EventService es) {
		this.es = es;
	}
	
	@PostMapping
	public ResponseEntity<String> save(@RequestBody EventDTO event){
		String key = es.save(event);
		
		return ResponseEntity.ok().body(key);
	}
	

}
