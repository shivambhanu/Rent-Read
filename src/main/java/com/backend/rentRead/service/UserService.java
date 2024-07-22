package com.backend.rentRead.service;

import com.backend.rentRead.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.backend.rentRead.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //here we are using email instead of username
        return userRepository.findByEmail(email);
    }
}
