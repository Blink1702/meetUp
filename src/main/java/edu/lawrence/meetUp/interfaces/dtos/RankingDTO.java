package edu.lawrence.meetUp.interfaces.dtos;

import edu.lawrence.meetUp.entities.Ranking;

public class RankingDTO {
	private String profile;
	private Integer pingpong;
	private Integer tennis;
	private Integer pickleball;
	private Integer badminton; 


	public RankingDTO() {}

	public RankingDTO(Ranking core) {
		profile = core.getProfile().getProfileid().toString();
		pingpong = core.getPingpong();
		tennis = core.getTennis();
		pickleball = core.getPickleball();
		badminton = core.getBadminton();
	
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public Integer getPingpong() {
		return pingpong;
	}

	public void setPingpong(Integer pingpong) {
		this.pingpong = pingpong;
	}

	public Integer getTennis() {
		return tennis;
	}

	public void setTennis(Integer tennis) {
		this.tennis = tennis;
	}

	public Integer getPickleball() {
		return pickleball;
	}

	public void setPickleball(Integer pickleball) {
		this.pickleball = pickleball;
	}

	public Integer getBadminton() {
		return badminton;
	}

	public void setBadminton(Integer badminton) {
		this.badminton = badminton;
	}
	
	
}
