package com.example.mode.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.mode.models.User;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
@Transactional
public class UserDaoImp implements UserDao{

	
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<User> getUsers() {
		
		String query = "FROM User";
		return entityManager.createQuery(query).getResultList();

	}

	@Override
	public void delete(Long id) {
		
		User user = entityManager.find(User.class, id);
		entityManager.remove(user);
	}

	@Override
	public void register(User user) {

		entityManager.merge(user);
		
	}

	@Override
	public User verification(User user) {
		String query = "FROM User WHERE mail = :mail";
		List<User> list = entityManager.createQuery(query)
				.setParameter("mail", user.getMail())
				.getResultList();
		
		if (list.isEmpty()) {
			return null;
		}
		
		String passwordHashed = list.get(0).getPassword();
		
		Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2i);
		if (argon2.verify(passwordHashed, user.getPassword())) {
			return list.get(0);
		}
		return null;
		
	}
	

}
