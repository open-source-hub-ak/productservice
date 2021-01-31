package com.opensource.productservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.opensource.productservice.model.User;
import com.opensource.productservice.repos.UserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepo.findByEmail(username);
		if (user==null) {
			throw new UsernameNotFoundException("User not found for email :"+username);
			
		}
		else {
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getRoles());
		}
	}

}
