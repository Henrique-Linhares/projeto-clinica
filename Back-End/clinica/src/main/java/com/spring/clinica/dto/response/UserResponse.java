package com.spring.clinica.dto.response;

import com.spring.clinica.entity.enums.UserRole;

public record UserResponse(Long id, String login, String senha, UserRole role) {
    
}
