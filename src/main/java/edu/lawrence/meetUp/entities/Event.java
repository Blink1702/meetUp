package edu.lawrence.meetUp.entities;

import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import edu.lawrence.meetUp.interfaces.dtos.EventDTO;
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
	@ManyToOne
	@JoinColumn(name="host")
	private User host;
	private String time;
	private String place;
	private String sport;
	
	public Event(EventDTO core) {
		host = core.getHost();
		participant = core.getParticipant();
		time = core.getTime();
		place = core.getPlace();	
		sport = core.getSport();
	}

	
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
	
	public User getHost() {
		return host;
	}
	
	public void setTime(User host) {
		this.host = host;
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


	public String getSport() {
		return sport;
	}


	public void setSport(String sport) {
		this.sport = sport;
	}
}
