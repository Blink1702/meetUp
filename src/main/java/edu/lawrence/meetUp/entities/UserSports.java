package edu.lawrence.meetUp.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;

@Embeddable
public class UserSports {

    @ElementCollection(targetClass = String.class)
    @Column(name = "sport") 
    private List<String> sport;

    public List<String> getSport(){
		return sport;
	}
	
	public void setSport(List<String> sport) {
		this.sport = sport;
	}
}