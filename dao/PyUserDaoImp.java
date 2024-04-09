package com.example.mode.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.mode.models.PyUser;
import com.example.mode.models.User;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
@Transactional
public class PyUserDaoImp implements PyUserDao{

	
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<PyUser> getPyUsers() {
		
		String query = "FROM PyUser";
		return entityManager.createQuery(query).getResultList();

	}

	@Override
	public void pyDelete(Long pyId) {
		
		PyUser pyUser = entityManager.find(PyUser.class, pyId);
		entityManager.remove(pyUser);
	}
//
	@Override
	public void pyRegister(PyUser pyUser) {

		entityManager.merge(pyUser);
		
	}

	@Override
	public PyUser pyVerification(PyUser pyUser) {
		String query = "FROM PyUser WHERE pyNit = :pyNit";
		List<PyUser> list = entityManager.createQuery(query)
				.setParameter("pyNit", pyUser.getPyNit())
				.getResultList();
		
		if (list.isEmpty()) {
			return null;
		}
		
		String passwordHashed = list.get(0).getPyPassword();
		
		Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2i);
		if (argon2.verify(passwordHashed, pyUser.getPyPassword())) {
			return list.get(0);
		}
		return null;
		
	}
	

}
