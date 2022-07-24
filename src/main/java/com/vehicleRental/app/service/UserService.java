package com.vehicleRental.app.service;

import com.vehicleRental.app.entities.User;
import com.vehicleRental.app.payloads.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import springfox.documentation.swagger2.mappers.ModelMapper;

import java.util.List;

public interface UserService {

	UserDto createUser(UserDto user);

	UserDto updateUser(UserDto user, Long userId);

	UserDto getUserById(Long userId);

	List<UserDto> getAllUsers();

	void deleteUser(Long userId);
}
