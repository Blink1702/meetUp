package edu.lawrence.meetUp.repositories;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.lawrence.meetUp.entities.Profile;
import edu.lawrence.meetUp.entities.User;

public interface ProfileRepository extends JpaRepository<Profile,UUID> {
	List<Profile> findBySport(String sport);
	List<Profile> findByLongitudeAndLatitudeAndSport(String center_long,String center_lat,String sport);

}
