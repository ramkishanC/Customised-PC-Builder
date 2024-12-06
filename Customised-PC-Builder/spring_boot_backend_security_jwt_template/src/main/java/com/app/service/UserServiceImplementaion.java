package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.config.JwtTokenProvider;
import com.app.entity.User;
import com.app.exception.UserException;
import com.app.repository.UserRepository;

@Service
public class UserServiceImplementaion implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JwtTokenProvider jwtProvider;

	@Override
	public User findUserById(Long userId) throws UserException
	{
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent())
		{
			return user.get();
		}
		throw new UserException("User not found with id"+userId);
	}
	
	@Override
	public User findUserProfileByJwt(String jwt ) throws UserException

	{
		String email = jwtProvider.getEmailFromJwtToken(jwt);
		User user = userRepository.findByEmail(email);
		if(user==null)
		{
			throw new UserException("User not found with email"+email);
			
		}
		
		return user;
	}
	
//	@Override
//	public List<User> findAllCustomers() {
//		return null;
//	}

	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAllByOrderByCreatedAtDesc();
	}
}
