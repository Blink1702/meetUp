package edu.lawrence.meetUp.entities;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

public class Profile {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(columnDefinition = "VARCHAR(45)")
	@JdbcTypeCode(SqlTypes.VARCHAR)
	private UUID profileid;
	@OneToOne
	@JoinColumn(name="user")
	@MapsId
	private User user;
	private String name;
	private String email;
	private String phone;
	private String location;
	private int rank;
	private List<String> sport;
	
	public Profile() {
	}
	
	/*
	public Profile(ProfileDTO) {
		
	}
	*/
	
	public UUID getProfileid(){
		return profileid;
	}
	
	public void setProfileid(UUID profileid) {
		this.profileid = profileid;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public int getRank() {
		return rank;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public List<String> getSport(){
		return sport;
	}
	
	public void setSport(List<String> sport) {
		this.sport = sport;
	}
}
