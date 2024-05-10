package edu.lawrence.meetUp.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.lawrence.meetUp.entities.User;

public interface UserRepository extends JpaRepository<User,UUID>{
	List<User> findByName(String name);
}
