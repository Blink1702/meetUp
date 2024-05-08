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
import jakarta.persistence.Table;

@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(columnDefinition = "VARCHAR(45)")
	@JdbcTypeCode(SqlTypes.VARCHAR)
	private UUID userid;
	private String username;
	private String password;
	private List<String> sport;
	private String location;
	private String rank;
	
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
	
	public void setSport(List<String> sport) {
		this.sport = sport;
	}
	
	public List<String> getSport(){
		return sport;
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}
	

	
}
