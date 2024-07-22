package com.backend.rentRead.controller.exchanges.request;

import com.backend.rentRead.model.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Role role;
}
