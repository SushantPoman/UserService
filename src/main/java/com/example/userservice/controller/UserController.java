package com.example.userservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.orderservice.controller.OrderController;
import com.example.userservice.dto.UserDto;
import com.example.userservice.model.User;
import com.example.userservice.service.UserService;

@RestController
public class UserController {
	
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

	
	@Autowired
	UserService userService;
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUser(){
		return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
	}
	
	@GetMapping("/username/{name}")
	public ResponseEntity<User> getUserByUsername(@PathVariable String name){
		return new ResponseEntity<User>(userService.findUserByUsername(name), HttpStatus.OK);
	}
	

	@GetMapping("/userByAddress/{add}")
	public ResponseEntity<List<User>> getUserByAddress(@PathVariable String add){
		return new ResponseEntity<List<User>>(userService.findUserByAddress(add), HttpStatus.OK);
	}
	

	@GetMapping("/userByEmail/{email}")
	public ResponseEntity<User> getUserByEmail(@PathVariable String email){
		return new ResponseEntity<User>(userService.findUserByEmail(email), HttpStatus.OK);
	}
	
	@GetMapping("/emailByName/{username}")
	public ResponseEntity<String> getEmailByUsername(@PathVariable String username){
		return new ResponseEntity<String>(userService.findEmailByUsername(username), HttpStatus.OK);
	}
	
	
	@PostMapping("/save")
	public ResponseEntity<User> saveUser(@RequestBody User user){
		return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.OK);
	}
	
	
//	From orderservice
	@GetMapping("/userGreet")
	public ResponseEntity<String> greetFromOrderService() {
		return new ResponseEntity<String>(userService.getGreet(), HttpStatus.OK);

	}
	
	@GetMapping("/orderByName/{name}")
	public ResponseEntity<UserDto> getOrdersByUsername(@PathVariable String name) {
		log.info("From UserService");
		return new ResponseEntity<UserDto>(userService.getOrders(name), HttpStatus.OK);

	}
	

}
