package edu.lawrence.meetUp.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.lawrence.meetUp.entities.Event;

public interface EventRepository extends JpaRepository<Event,UUID>  {

}
