package com.spring.clinica.dto.response;

import com.spring.clinica.entity.User;

public record PacienteResponse(Long id, User user, Integer idade) {
    
}
