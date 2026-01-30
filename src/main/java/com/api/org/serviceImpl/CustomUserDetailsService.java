package com.api.org.serviceImpl;

import com.api.org.constants.AppConstants;
import com.api.org.security.*;
import com.api.org.exception.ResourceNotFoundException;
import com.api.org.model.Users;
import com.api.org.repository.UserRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
    	 Users user = userRepository.findByUserName(username)
    	            .orElseThrow(() ->
    	                    new UsernameNotFoundException(AppConstants.RESOURCE_NOT_FOUND));

    	    return UserPrincipal.create(user);
       }

    @Transactional
    public UserDetails loadUserById(Long id) {
        Users user = userRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(AppConstants.RESOURCE_NOT_FOUND)
        );

        return UserPrincipal.create(user);
    }
    
    @Transactional
    public Users loadUserByUserId(Long id) {
        Users user = userRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(AppConstants.RESOURCE_NOT_FOUND)
        );

        return user;
    }
 
}