package com.vehicleRental.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vehicleRental.app.dao.UserDao;
import com.vehicleRental.app.model.User;

@Service
public class UserService {

	@Autowired
	UserDao userDao;

	public List<User> getAll() {
		return userDao.findAll();
	}

	public Optional<User> getById(long id) {
		return userDao.findById(id);
	}

	public User save(User newUser) {
		return userDao.save(newUser);
	}

	public void delete(long id) {
		userDao.deleteById(id);
	}

}
