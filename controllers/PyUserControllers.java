package com.example.mode.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.mode.dao.PyUserDao;
import com.example.mode.models.PyUser;
import com.example.mode.utils.JWTUtil;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@RestController
public class PyUserControllers {
	
	@Autowired
	private PyUserDao pyUserDao;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	

	
	@RequestMapping(value = "api/pyusers", method = RequestMethod.GET)
	public List<PyUser> getPyUsers(	@RequestHeader(value="Authorization") String token) {
		
		if (!tokenValidation(token)) {return null;};
		
		return pyUserDao.getPyUsers();
	}
	
	
	
	@RequestMapping(value = "api/pyusers", method = RequestMethod.POST)
	public void pyRegisterUsers(@RequestBody PyUser pyUser) {
		Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2i);
		String hash = argon2.hash(1, 1024, 1, pyUser.getPyPassword());
		pyUser.setPyPassword(hash);
		pyUserDao.pyRegister(pyUser);
	}
	
	
	
	@RequestMapping(value = "api/pyusers/{pyId}", method = RequestMethod.DELETE)
	public void pyDelete(@RequestHeader(value="Authorization") String token,
							@PathVariable Long pyId) {
		if (!tokenValidation(token)) {return;};
		pyUserDao.pyDelete(pyId);
	}

	private boolean tokenValidation(String token) {
		String userId = jwtUtil.getKey(token);
		return userId != null;
	}
	
	

}
