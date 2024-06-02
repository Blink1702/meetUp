package edu.lawrence.meetUp.interfaces.dtos;

import edu.lawrence.meetUp.entities.Event;
import edu.lawrence.meetUp.entities.User;

public class EventDTO {
	private String eventid;
	private String host;
	private String participant;
	private String time;
	private String place;
	private String sport;
	
	public EventDTO() {}
	
	public EventDTO(Event core) {
		eventid = core.getEventid().toString();
		host = core.getHost().getUsername();
		participant = core.getParticipant().getUsername();
		time = core.getTime();
		place = core.getPlace();
		sport = core.getSport();
	}
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getParticipant() {
		return participant;
	}

	public void setParticipant(String participant) {
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
	
	public String getSport() {
		return sport;
	}


	public void setSport(String sport) {
		this.sport = sport;
	}

	public String getEventid() {
		return eventid;
	}

	public void setEventid(String eventid) {
		this.eventid = eventid;
	}


}
