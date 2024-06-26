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

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import edu.lawrence.meetUp.entities.Event;
import edu.lawrence.meetUp.interfaces.dtos.EventDTO;
import edu.lawrence.meetUp.security.MeetupUserDetails;
import edu.lawrence.meetUp.security.WrongUserException;
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
	public ResponseEntity<EventDTO> save(Authentication authentication,@RequestBody EventDTO event){
		MeetupUserDetails details = (MeetupUserDetails) authentication.getPrincipal();
		UUID id = UUID.fromString(details.getUsername());
		event.setHost(id.toString());
		event.setParticipant("");
		String key;
		try{
			key = es.save(event);
		} catch(WrongUserException ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(event);
		}
		event.setEventid(key);
		
		return ResponseEntity.ok().body(event);
	}
	
	
	@PostMapping("/participate/{eventid}")
	public ResponseEntity<String> addParticipant(Authentication authentication,@PathVariable("eventid") UUID eventid){
		MeetupUserDetails details = (MeetupUserDetails) authentication.getPrincipal();
		UUID participantid = UUID.fromString(details.getUsername());
		
		try {
			es.setParticipant(eventid,participantid);
		} catch(WrongUserException ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("\"Invalid user id\"");
		}
		
		return ResponseEntity.ok().body("\"Signed up for event\"");
	}
	
	
	@GetMapping(params = {"place"})
	public ResponseEntity<List<EventDTO>> findEventByPlace(@RequestParam(value = "place")String place){
		List<Event> event = es.findEventByPlace(place);
		List<EventDTO> results = new ArrayList<EventDTO>();
		for(Event e : event) {
			results.add(new EventDTO(e));
		}
		return ResponseEntity.ok().body(results);
	}
	
	@GetMapping(params = {"time"})
	public ResponseEntity<List<EventDTO>> findEventByTime(@RequestParam(value = "time")String time){
		List<Event> event = es.findEventByTime(time);
		List<EventDTO> results = new ArrayList<EventDTO>();
		for(Event e : event) {
			results.add(new EventDTO(e));
		}
		return ResponseEntity.ok().body(results);
	}
	
	@GetMapping(params = {"sport"})
	public ResponseEntity<List<EventDTO>> findEventBySport(@RequestParam(value = "sport")String sport){
		List<Event> event = es.findEventBySport(sport);
		List<EventDTO> results = new ArrayList<EventDTO>();
		for(Event e : event) {
			results.add(new EventDTO(e));
		}
		return ResponseEntity.ok().body(results);
	}
	
	

}
