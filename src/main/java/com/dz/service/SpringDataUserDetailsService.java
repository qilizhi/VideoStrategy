package com.dz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.dz.entities.User;
import com.dz.repository.UserResposity;

@Component
public class SpringDataUserDetailsService implements UserDetailsService {
	@Autowired
	private UserResposity userResposity;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user=userResposity.findOneByUsername(username);
		List<SimpleGrantedAuthority> grantedAuthorities=new ArrayList<SimpleGrantedAuthority>();
		SimpleGrantedAuthority granted=new SimpleGrantedAuthority("admin");
		grantedAuthorities.add(granted);
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),grantedAuthorities);
	}

}
