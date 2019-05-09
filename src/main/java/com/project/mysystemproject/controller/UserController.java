package com.project.mysystemproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import com.project.mysystemproject.model.User;
import com.project.mysystemproject.repository.UserRepository;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
	
	@Autowired
    UserRepository userRepository;
    
	@GetMapping(path = "/users",produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<User> getUsers(@RequestParam(value = "username", required = false) String userName) 
	{		
		if (userName == null || userName.isEmpty()) 
		{
			return userRepository.findAll();		
		} 
		else 
		{
			return userRepository.findByusername(userName);
		}		
	}
	
	@PostMapping(path = "/users", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, String> addUser(@Valid @RequestBody User user) 
	{
		Map<String, String> responseMap = new HashMap<String,String>();
		List<User> listOfUser = userRepository.findByusername(user.getUsername());
		if (listOfUser.size() == 0) // Its a new user 
		{
			userRepository.save(user);
			responseMap.put("result","Successfully registered");
			return responseMap;
			//return true;
		}
		responseMap.put("result","User already exists!");
		return responseMap;
		//return false;
	}
	
	@GetMapping(path = "/validUsers", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, String> isValidUser(@RequestParam(value = "username") String username,@RequestParam(value = "password") String password)
	{
		Map<String, String> responseMap = new HashMap<String,String>();
		List<User> listOfUser = userRepository.findByusernameAndpassword(username,password);
		if(listOfUser.size() > 0)
		{
			responseMap.put("result","Successfully Logged In");
			return responseMap;
		}
		
		responseMap.put("result","Invalid credentials");
		return responseMap;
		
	}
    
   
}
