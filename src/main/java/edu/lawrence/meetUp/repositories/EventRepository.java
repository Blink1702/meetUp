package edu.lawrence.meetUp.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.lawrence.meetUp.entities.Event;
import edu.lawrence.meetUp.entities.User;

public interface EventRepository extends JpaRepository<Event,UUID>  {
	List<Event> findByTime(String time);
	List<Event> findByPlace(String place);
	List<Event> findBySport(String sport);
}
