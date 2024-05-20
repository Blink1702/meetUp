package edu.lawrence.meetUp.entities;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.context.annotation.DependsOn;

import edu.lawrence.meetUp.interfaces.dtos.ProfileDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="profiles")
public class Profile {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(columnDefinition = "VARCHAR(255)")
	@JdbcTypeCode(SqlTypes.VARCHAR)
	private UUID profileid;
	private String name;
	private String email;
	private String phone;
	private String latitude;
	private String longitude;
	@OneToOne
	@JoinColumn(name="user")
	private User user;
	private String sport;
	
	public Profile() {}
	
	public Profile(ProfileDTO core){
		name = core.getName();
		email = core.getEmail();
		phone = core.getPhone();
		latitude = core.getLatitude();
		longitude = core.getLongitude();
		sport = core.getSport();
	}

	
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
	
		
	public String getSport(){
		return sport;
	}
	
	public void setSport(String sport) {
		this.sport = sport;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
}
