package com.spring.clinica.dto.response;

import java.util.List;

import com.spring.clinica.entity.User;

public record PacienteResponse(Long id, String login, Integer idade, List<String> consultas) {
}
