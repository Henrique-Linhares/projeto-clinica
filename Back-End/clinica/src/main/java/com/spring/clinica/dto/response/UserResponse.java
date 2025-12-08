package com.spring.clinica.dto.response;

import java.util.Set;

import com.spring.clinica.entity.Role;

public record UserResponse(Long id, String email, String senha, Set<Role> roles) {
    
}
