package edu.lawrence.meetUp.interfaces.dtos;

import java.util.List;

import edu.lawrence.meetUp.entities.Profile;
import edu.lawrence.meetUp.entities.Ranking;

public class ProfileDTO {
	private String user;
	private String name;
	private String email;
	private String phone;
	private String location;
	private String sport;
	
	public ProfileDTO() {}
	
	public ProfileDTO(Profile core) {
		user = core.getUser().getUserid().toString();
		name = core.getName();
		email = core.getEmail();
		phone = core.getPhone();
		location = core.getLocation();
		sport = core.getSport();
;
		}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
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
	
	public String getSport() {
		return sport;
	}
	public void setSport(String	 sport) {
		this.sport = sport;
	}	

}
