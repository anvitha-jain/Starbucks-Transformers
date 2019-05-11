package com.project.mysystemproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.project.mysystemproject.exception.UserNotFoundException;
import com.project.mysystemproject.model.User;
import com.project.mysystemproject.repository.UserRepository;
import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

	@Autowired
    UserRepository userRepository;
    
	@GetMapping("/users")
	@ResponseBody
	public List<User> getUsers(@RequestParam(value = "username", required = false) String userName) 
	{

		if (userName == null || userName.isEmpty()) {
			return userRepository.findAll();
		} else {
			return userRepository.findByusername(userName);
		}
	}
	
	@PostMapping("/users")
	public boolean addUser(@Valid @RequestBody User user) 
	{
		List<User> listOfUser = userRepository.findByusername(user.getUsername());
		if (listOfUser.size() == 0) // Its a new user 
		{
			userRepository.save(user);
			return true;
		}

		return false;
	}
	
	@GetMapping("/validUsers")
	@ResponseBody
	public boolean isValidUser(@RequestParam(value = "username") String username,@RequestParam(value = "password") String password)
	{
		List<User> listOfUser = userRepository.findByusernameAndpassword(username,password);
		if(listOfUser.size() > 0)
		{
			return true;
		}
		
		return false;
		
	}
    
   
}
