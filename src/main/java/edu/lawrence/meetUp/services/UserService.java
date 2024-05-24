package edu.lawrence.meetUp.services;

import java.util.ArrayList;
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
	
	public List<Profile> findProfileByLocationAndSport(String strcen_lon, String strcen_lat,String sport)  {
		List<Profile> existing = profileRepository.findBySport(sport);
		if(existing.size() <= 0) 
			return null;
		
		Double cen_lat = Double.parseDouble(strcen_lat);
		Double cen_lon = Double.parseDouble(strcen_lon);
		
		List<Profile> filteredExisting = new ArrayList<Profile>();
    	
		for(int i=0;i< existing.size();i++) {
			String strlat = existing.get(i).getLatitude();
			String strlon = existing.get(i).getLongitude();
			Double lat = Double.parseDouble(strlat);
			Double lon = Double.parseDouble(strlon);
			
			if(filterProfileByLocation(cen_lat,cen_lon,lat,lon))
				filteredExisting.add(existing.get(i));
		}
		
		return filteredExisting;
		
	}
	public boolean filterProfileByLocation(Double cen_lat, Double cen_lon, Double lat, Double lon){
		Double cen_latRad = Math.toRadians(cen_lat);
		Double latRad = Math.toRadians(lat);
		Double cen_lonRad = Math.toRadians(cen_lon);
		Double lonRad = Math.toRadians(lon);
		System.out.println(cen_latRad);
		System.out.println(latRad);
		System.out.println(cen_lonRad);
		System.out.println(lonRad);
		Double x = (lonRad - cen_lonRad) * Math.cos((cen_latRad + latRad)/2);
		Double y = (latRad - cen_latRad);
		Double distance = Math.sqrt((x*x)+(y*y)) * 6371;
		System.out.println(distance);
		
		if(distance <= 5) 
			return true;
		else
			return false;
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
