package com.spring.clinica.dto.response;

import java.util.List;

import com.spring.clinica.entity.Consulta;
import com.spring.clinica.entity.User;

public record MedicoResponse(Long id, User user, String especialidade, List<Consulta> consultas) {
    
}
