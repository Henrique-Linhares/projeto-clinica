package com.spring.clinica.dto.request;

import com.spring.clinica.entity.User;

public record PacienteRequest(User user, Integer idade) {
    
}
