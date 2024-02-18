package com.example.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.userservice.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	public User findByUsername(String username);

	public List<User> findByAddress(String address);
	
	@Query(value = "select * from user where email = :email", nativeQuery = true)
	public User getUserByEmail(String email);

//	Custom methods if named/query method not worked 
	@Query(value = "select email from user where username = :username", nativeQuery = true)
	public String getEmailByUsername(String username);

	
	
}
