package edu.lawrence.meetUp.services;

public class DuplicateException extends Exception  {
	public DuplicateException() {
		super("Attempting to insert a duplicate object.");
	}
}
