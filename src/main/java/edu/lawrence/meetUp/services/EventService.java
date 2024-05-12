package edu.lawrence.meetUp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.lawrence.meetUp.interfaces.dtos.EventDTO;
import edu.lawrence.meetUp.repositories.UserRepository;

@Service
public class EventService {
	@Autowired
	UserRepository userRepository;

	
	public String save(EventDTO event) {
		
	}
	
	public String findEventByTime(String time) {
		
	}
	
	public String findEventByPlace(String place) {
		
	}
}
