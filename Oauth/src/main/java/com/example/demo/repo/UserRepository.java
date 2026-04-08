package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.User;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Long>{

	User findByClientId(String clientId);
	
	User findByEmail(String email);
}
