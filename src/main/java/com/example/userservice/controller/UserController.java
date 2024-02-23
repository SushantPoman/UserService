package com.example.userservice.controller;

import java.util.List;

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

import com.example.userservice.dto.UserDto;
import com.example.userservice.model.User;
import com.example.userservice.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class UserController {
	
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
	
	int count =1;
//	@CircuitBreaker(name = "orderfallback", fallbackMethod = "orderFallbackMethod")
	@Retry(name = "orderfallback", fallbackMethod = "orderFallbackMethod")
	@GetMapping("/orderByName/{name}")
	public ResponseEntity<UserDto> getOrdersByUsername(@PathVariable String name) {
		System.out.println(" Retrying ... "+count);
		count++;
		return new ResponseEntity<UserDto>(userService.getOrders(name), HttpStatus.OK);
	}
	
	public ResponseEntity<Object> orderFallbackMethod(String name, Throwable t){
		System.out.println("Order Fallback");
		return new ResponseEntity<Object>("<h1>OrderService is not responding, please try again</h1>", HttpStatus.GATEWAY_TIMEOUT);
	}
	
	
	

}
