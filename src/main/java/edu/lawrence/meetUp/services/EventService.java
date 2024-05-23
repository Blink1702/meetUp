package edu.lawrence.meetUp.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.lawrence.meetUp.entities.User;
import edu.lawrence.meetUp.entities.Event;
import edu.lawrence.meetUp.interfaces.dtos.EventDTO;
import edu.lawrence.meetUp.repositories.EventRepository;
import edu.lawrence.meetUp.repositories.UserRepository;
import edu.lawrence.meetUp.security.WrongUserException;

@Service
public class EventService {
	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	UserRepository userRepository;

	
	public String save(EventDTO event) throws WrongUserException {
		Optional<User> maybeUser = userRepository.findById(UUID.fromString(event.getHost()));
		if(!maybeUser.isPresent()) 
			throw new WrongUserException();
		Event newEvent = new Event(event);
		eventRepository.save(newEvent);
		
		return newEvent.getEventid().toString();
	}
	
	public String setParticipant(UUID eventid, UUID participantid) throws WrongUserException{
		Optional<User> maybeUser = userRepository.findById(participantid);
		if(!maybeUser.isPresent()) 
			throw new WrongUserException();
		User participant = maybeUser.get();
		
		Optional<Event> maybeEvent = eventRepository.findById(eventid);
		if(!maybeEvent.isPresent())
			throw new WrongUserException();
		Event existingEvent = maybeEvent.get();
		existingEvent.setParticipant(participant);
		eventRepository.save(existingEvent);
		
		return "Participant saved";
	}
	
	public List<Event> findEventByTime(String time) {
		return eventRepository.findByTime(time);
	}
	
	public List<Event> findEventByPlace(String place) {
		return eventRepository.findByPlace(place);
	}
	
	public List<Event> findEventBySport(String sport){
		return eventRepository.findBySport(sport);
	}
}
