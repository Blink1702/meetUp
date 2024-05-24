package edu.lawrence.meetUp.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.lawrence.meetUp.entities.Profile;
import edu.lawrence.meetUp.entities.Ranking;
import edu.lawrence.meetUp.entities.User;
import edu.lawrence.meetUp.interfaces.dtos.ProfileDTO;
import edu.lawrence.meetUp.interfaces.dtos.RankingDTO;
import edu.lawrence.meetUp.interfaces.dtos.UserDTO;
import edu.lawrence.meetUp.repositories.ProfileRepository;
import edu.lawrence.meetUp.repositories.RankingRepository;
import edu.lawrence.meetUp.repositories.UserRepository;
import edu.lawrence.meetUp.security.PasswordService;
import edu.lawrence.meetUp.security.WrongUserException;

@Service
public class UserService {
	@Autowired 
    PasswordService passwordService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProfileRepository profileRepository;
	
	@Autowired
	RankingRepository rankingRepository;
	
	
	public String save(UserDTO user) throws DuplicateException {
		List<User> existing = userRepository.findByUsername(user.getUsername());
		if(existing.size() > 0)
			throw new DuplicateException();
		
		User newUser = new User();
		newUser.setUsername(user.getUsername());
		String hash = passwordService.hashPassword(user.getPassword());
	    newUser.setPassword(hash);
		userRepository.save(newUser);
		return newUser.getUserid().toString();
	}
	
	public User findByUsernameAndPassword(String username,String password) {
		List<User> existing = userRepository.findByUsername(username);
		if(existing.size() != 1)
			return null;
		User u = existing.get(0);
		if(passwordService.verifyHash(password, u.getPassword())) {
        	u.setPassword("Undisclosed");
        } else {
        	u = null;
        }
        return u;	
	}
	
	public String saveProfile (ProfileDTO profile) throws DuplicateException, WrongUserException{
		Optional<User> maybeUser = userRepository.findById(UUID.fromString(profile.getUser().toString()));
		if(!maybeUser.isPresent())
			throw new WrongUserException();
		
		User user = maybeUser.get();
		if(user.getProfile() != null)
			throw new DuplicateException();
		
		Profile newProfile = new Profile(profile);
		newProfile.setUser(user);
		profileRepository.save(newProfile);
		return newProfile.getProfileid().toString();
	}
	
	public Profile findProfile(UUID userid) {
		Optional<User> maybeUser = userRepository.findById(userid);
		if(!maybeUser.isPresent())
			return null;
		
		return maybeUser.get().getProfile();
	}
	
	public List<Profile> findProfileBySport(String sport) {
		List<Profile> existing = profileRepository.findBySport(sport);
		if(existing.size() <= 0) 
			return null;
		
		return existing;
		
	}
	
	public List<Profile> findProfileByLocationAndSport(String longitude, String latitude,String sport) {
		List<Profile> existing = profileRepository.findByLongitudeAndLatitudeAndSport(longitude,latitude,sport);
		if(existing.size() <= 0) 
			return null;
		return existing;
	}
	
	public String setRanking(UUID userid, RankingDTO ranking) throws DuplicateException, WrongUserException{
		Optional<User> maybeUser = userRepository.findById(userid);
		if(!maybeUser.isPresent())
			throw new WrongUserException();
		
		User user = maybeUser.get();
		if(user.getRanking() != null)
			throw new DuplicateException();
		
		Ranking newRanking = new Ranking(ranking);
		newRanking.setUser(user);
		rankingRepository.save(newRanking);
		
		return newRanking.getRankingid().toString();
	}
	

}
