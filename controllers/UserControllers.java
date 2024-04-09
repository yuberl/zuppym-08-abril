package com.example.mode.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.mode.dao.UserDao;
import com.example.mode.models.User;
import com.example.mode.utils.JWTUtil;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@RestController
public class UserControllers {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@RequestMapping(value = "api/user/{id}")
	public User getUser(@PathVariable Long id) {
		
		User user = new User();
		user.setId(1L);
		user.setName("Santiago");
		user.setLastName("Vega");
		user.setNumber("132");
		user.setMail("correo@correo.com");
		
		return user;
	}
	
	@RequestMapping(value = "api/users", method = RequestMethod.GET)
	public List<User> getUsers(@RequestHeader(value="Authorization") String token) {
		
		if (!tokenValidation(token)) {return null;};
		
		return userDao.getUsers();
	}
	
	
	
	@RequestMapping(value = "api/users", method = RequestMethod.POST)
	public void registerUsers(@RequestBody User user) {
		Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2i);
		String hash = argon2.hash(1, 1024, 1, user.getPassword());
		user.setPassword(hash);
		userDao.register(user);
	}
	
	@RequestMapping(value = "api/users/{id}", method = RequestMethod.DELETE)
	public void delete(@RequestHeader(value="Authorization") String token,
						@PathVariable Long id) {
		if (!tokenValidation(token)) {return;};
		userDao.delete(id);
	}

	private boolean tokenValidation(String token) {
		String userId = jwtUtil.getKey(token);
		return userId != null;
	}
	
	

}
