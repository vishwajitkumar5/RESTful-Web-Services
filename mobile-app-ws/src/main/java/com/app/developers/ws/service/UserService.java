package com.app.developers.ws.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.app.developers.ws.shared.dto.UserDto;

public interface UserService extends UserDetailsService{
	UserDto createUser(UserDto user);
	UserDto getUser(String email);
	UserDto getUserByUserId(String UserId);
	UserDto updateUser(String UserId,UserDto user);
	void deleteUser(String userId);
	List<UserDto> getUsers(int page,int limit);
}
