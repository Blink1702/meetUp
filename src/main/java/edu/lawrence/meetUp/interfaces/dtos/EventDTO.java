package edu.lawrence.meetUp.interfaces.dtos;

import edu.lawrence.meetUp.entities.Event;
import edu.lawrence.meetUp.entities.User;

public class EventDTO {
	private User participant;
	private String time;
	private String place;
	
	public EventDTO() {}
	
	public EventDTO(Event core) {
		participant = core.getParticipant();
		time = core.getTime();
		place = core.getPlace();
	}

	public User getParticipant() {
		return participant;
	}

	public void setParticipant(User participant) {
		this.participant = participant;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	

}