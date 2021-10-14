package com.temire.springbootexample.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if ("temire".equals(username)) {
			return new User("temire", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
					new ArrayList<>());
		}
		else if ("systemspec".equals(username)) {
			return new User("systemspec", "#2a$10$slYQmyNdGzTn7ZLDXBChFOC9h6kFjAqPhccnP6DxlWXx2lPk1C3G6",
					new ArrayList<>());
		}
		else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

}