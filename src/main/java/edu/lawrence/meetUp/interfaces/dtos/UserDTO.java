package edu.lawrence.meetUp.interfaces.dtos;

public class UserDTO {
	private String username;
	private String password;
	
	public UserDTO() {}

	public String getUsername() {
		return username;
	}

	public void setName(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}