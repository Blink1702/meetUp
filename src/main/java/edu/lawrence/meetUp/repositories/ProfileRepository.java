package edu.lawrence.meetUp.repositories;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.lawrence.meetUp.entities.Profile;

public interface ProfileRepository extends JpaRepository<Profile,UUID> {

}
