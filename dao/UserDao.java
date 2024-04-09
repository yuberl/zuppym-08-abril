package com.example.mode.dao;

import java.util.List;
import com.example.mode.models.User;

public interface UserDao {
	
	List<User> getUsers();

	void delete(Long id);

	void register(User user);

	User verification(User user);

}
