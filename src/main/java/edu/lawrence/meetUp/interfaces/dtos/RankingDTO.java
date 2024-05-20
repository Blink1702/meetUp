package edu.lawrence.meetUp.interfaces.dtos;

import edu.lawrence.meetUp.entities.Ranking;

public class RankingDTO {
	private String user;
	private Integer pingpong;
	private Integer tennis;
	private Integer pickleball;
	private Integer badminton; 


	public RankingDTO() {}

	public RankingDTO(Ranking core) {
		user = core.getUser().getUserid().toString();
		pingpong = core.getPingpong();
		tennis = core.getTennis();
		pickleball = core.getPickleball();
		badminton = core.getBadminton();
	
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
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
