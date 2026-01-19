package com.spring.clinica.dto.response;

import java.util.List;

public record PacienteResponse(Long id, String login, Integer idade, List<String> consultas) {
}
