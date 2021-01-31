package com.opensource.productservice.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.opensource.productservice.model.User;

public interface UserRepo extends JpaRepository<User, Long> {

	User findByEmail(String email);
}
