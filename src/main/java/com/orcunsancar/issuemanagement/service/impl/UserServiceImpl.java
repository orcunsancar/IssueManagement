package com.orcunsancar.issuemanagement.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.orcunsancar.issuemanagement.entity.User;
import com.orcunsancar.issuemanagement.repository.UserRepository;
import com.orcunsancar.issuemanagement.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;
	
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User save(User user) {
		// Business logic
		if(user.getEmail() == null) {
			throw new IllegalArgumentException("Username cannot be null");
		}
		user = userRepository.save(user);
		return user;
	}

	@Override
	public User getById(Long id) {
		return userRepository.getOne(id);
	}

	@Override
	public Page<User> getAllPageable(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	public User getByUsername(String userName) {
		return userRepository.findByUserName(userName);
	}

}
