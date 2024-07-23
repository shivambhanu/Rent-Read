package com.backend.rentRead.controller;

import com.backend.rentRead.controller.exchanges.request.AuthRequest;
import com.backend.rentRead.controller.exchanges.request.RegisterRequest;
import com.backend.rentRead.controller.exchanges.response.AuthResponse;
import com.backend.rentRead.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }



        //We don't usually need custom login endpoint for Basic Auth, but if you want to use it,
        // then remember that you need to put the credentials in header as well as json payload in the body to authorize

//    @PostMapping("/login")
//    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request){
//        return ResponseEntity.ok(authService.login(request));
//    }
}
