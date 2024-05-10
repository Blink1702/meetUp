package edu.lawrence.meetUp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.lawrence.meetUp.interfaces.dtos.UserDTO;
import edu.lawrence.meetUp.repositories.ProfileRepository;
import edu.lawrence.meetUp.repositories.UserRepository;

@Service
public class UserService {
	@Autowired 
    PasswordService passwordService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProfileRepository profileRepository;
	
	
	public String save(UserDTO user) throws DuplicateException {
		List<User> existing = userRepository.findByName(user.getUsername());
		if(existing.size() > 0)
			throw new DuplicateException();
		
		User newUser = new User();
		newUser.setName(user.getUsername());
		String hash = passwordService.hashPassword(user.getPassword());
	    newUser.setPassword(hash);
		userRepository.save(newUser);
		return newUser.getUserid().toString();
	}

}
