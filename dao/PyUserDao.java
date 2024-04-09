package com.example.mode.dao;

import java.util.List;

import com.example.mode.models.PyUser;
import com.example.mode.models.User;

public interface PyUserDao {
	
	List<PyUser> getPyUsers();

	void pyDelete(Long pyId);


	void pyRegister(PyUser pyUser);

	PyUser pyVerification(PyUser pyUser);

	

}