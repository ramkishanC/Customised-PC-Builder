package com.app.service;

import java.util.List;

import com.app.entity.User;
import com.app.exception.UserException;

public interface UserService {

	public User findUserById(Long userId) throws UserException;

	public User findUserProfileByJwt(String jwt) throws UserException;

	public List<User> findAllUsers();
}
