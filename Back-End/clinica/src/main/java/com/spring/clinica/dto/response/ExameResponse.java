package com.spring.clinica.dto.response;

import java.time.LocalDateTime;

import com.spring.clinica.entity.Consulta;

public record ExameResponse(Long id, String nome, String categoria, LocalDateTime data, Consulta consulta) {
    
}
