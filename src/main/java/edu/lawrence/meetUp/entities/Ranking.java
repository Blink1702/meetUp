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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="ranking")
public class Ranking {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(columnDefinition = "VARCHAR(255)")
	@JdbcTypeCode(SqlTypes.VARCHAR)
	private UUID rankingid;
	@OneToOne
	@JoinColumn(name="profile")
	private Profile profile;
	private Integer pingpong;
	private Integer tennis;
	private Integer pickleball;
	private Integer badminton;
	
	public UUID getRankingid() {
		return rankingid;
	}
	public void setRankingid(UUID rankingid) {
		this.rankingid = rankingid;
	}
	
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
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
