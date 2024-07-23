package com.backend.rentRead.service;

import com.backend.rentRead.controller.exchanges.request.AuthRequest;
import com.backend.rentRead.controller.exchanges.request.RegisterRequest;
import com.backend.rentRead.controller.exchanges.response.AuthResponse;
import com.backend.rentRead.model.User;
import com.backend.rentRead.model.enums.Role;
import com.backend.rentRead.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AuthService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request){
        if(request.getRole() == null){
            request.setRole(Role.USER);
        }

        User user = User.builder()
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        userRepository.save(user);

        return AuthResponse.builder().build();
    }


    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }



    //We don't usually need custom login endpoint for Basic Auth, but if you want to use it,
    // then remember that you need to put the credentials in header as well as json payload in the body to authorize

//    public AuthResponse login(AuthRequest request){
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
//        return AuthResponse.builder().build();
//    }
}
