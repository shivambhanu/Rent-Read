package com.backend.rentRead.controller;

import com.backend.rentRead.controller.exchanges.request.AuthRequest;
import com.backend.rentRead.controller.exchanges.request.RegisterRequest;
import com.backend.rentRead.controller.exchanges.response.AuthResponse;
import com.backend.rentRead.model.User;
import com.backend.rentRead.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(authService.getAllUsers());
    }


    @GetMapping("/")
	public String welcome() {
		return "Hello from AUTHENTICATED endpoint!";
	}

	@GetMapping("/admins")
	@PreAuthorize("hasRole('ADMIN')")
	public String welcomeAdmin() {
		return "Hello from ADMIN's endpoint!";
	}



    //We don't usually need custom login endpoint for Basic Auth, but if you want to use it,
    // then remember that you need to put the credentials in header as well as json payload in the body to authorize

//    @PostMapping("/login")
//    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request){
//        return ResponseEntity.ok(authService.login(request));
//    }
}
