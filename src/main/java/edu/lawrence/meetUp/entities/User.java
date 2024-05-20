package edu.lawrence.meetUp.entities;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(columnDefinition = "VARCHAR(255)")
	@JdbcTypeCode(SqlTypes.VARCHAR)
	private UUID userid;
	private String username;
	private String password;
	@OneToOne(mappedBy="user")
	private Profile profile;
	@OneToMany(mappedBy="participant")
	List<Event> event;
	@OneToOne(mappedBy="user")
	private Ranking ranking;
	
	public User() {}
	
	public UUID getUserid() {
		return userid;
	}

	public void setUserid(UUID userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Profile getProfile() {
		return profile;
	}
	
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	public List<Event> getEvent() {
		return event;
	}

	public void setEvent(List<Event> event) {
		this.event = event;
	}
	
	public Ranking getRanking() {
		return ranking;
	}
	
	public void setRanking(Ranking ranking) {
		this.ranking = ranking;
	}

	
}
