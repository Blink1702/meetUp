package edu.lawrence.meetUp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.lawrence.meetUp.entities.Event;
import edu.lawrence.meetUp.interfaces.dtos.EventDTO;
import edu.lawrence.meetUp.repositories.EventRepository;

@Service
public class EventService {
	@Autowired
	EventRepository eventRepository;

	
	public String save(EventDTO event) throws DuplicateException {
		List<Event> existing = eventRepository.findByHost(event.getHost());
		if(existing.size() > 0) 
			throw new DuplicateException();
		Event newEvent = new Event(event);
		eventRepository.save(newEvent);
		
		return newEvent.getEventid().toString();
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
