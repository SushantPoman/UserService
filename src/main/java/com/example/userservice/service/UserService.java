package com.example.userservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.userservice.dto.Orders;
import com.example.userservice.dto.UserDto;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value(value = "${orderservice.url}")
	String orderServiceUrl;
	
	public String getGreet() {
		return restTemplate.getForObject(orderServiceUrl+"/greet", String.class);
	}
	
	public UserDto getOrders(String username) {
		User user = findUserByUsername(username);
		UserDto dto = new UserDto();
		dto.setUsername(user.getUsername());
		dto.setAddress(user.getAddress());
		dto.setContact(user.getContact());
		dto.setEmail(user.getEmail());
		
		List<Orders> orders = restTemplate.getForObject(orderServiceUrl+"/orderByName/"+username, List.class);
		
		dto.setOrders(orders);
		
		return dto;
	}
	
	public List<User> getAllUsers(){
		return (List<User>) userRepository.findAll();
	}
	
//	public User saveUser(User user) {
//		return userRepository.save(user);
//	}

	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public List<User> findUserByAddress(String add) {
		return userRepository.findByAddress(add);
	}

	public User findUserByEmail(String email) {
		return userRepository.getUserByEmail(email);
	}

	public String findEmailByUsername(String username) {
		return userRepository.getEmailByUsername(username);
	}

}
