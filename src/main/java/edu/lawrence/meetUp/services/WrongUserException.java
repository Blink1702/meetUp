package edu.lawrence.meetUp.services;

public class WrongUserException extends Exception {
	public WrongUserException() {
		super("This action is not permitted for the current user.");
	}
}
