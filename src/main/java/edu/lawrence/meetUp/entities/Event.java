package edu.lawrence.meetUp.entities;

import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="event")
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(columnDefinition = "VARCHAR(45)")
	@JdbcTypeCode(SqlTypes.VARCHAR)
	private UUID eventid;
	@ManyToOne
	@JoinColumn(name="participant")
	private User participant;
	private String time;
	private String place;
	
	public Event() {
		
	}
	
	/*
	public Event(EventDTO) {
		
	}
	*/
	
	public UUID getEventid() {
		return eventid;
	}
	
	public void setEventid(UUID eventid) {
		this.eventid = eventid;
	}
	
	public User getParticipant() {
		return participant;
	}
	
	public void setParticipant(User participant) {
		this.participant = participant;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public String getPlace() {
		return place;
	}
	
	public void setPlace(String place) {
		this.place = place;
	}
}
