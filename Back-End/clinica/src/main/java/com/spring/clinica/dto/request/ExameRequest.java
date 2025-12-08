package com.spring.clinica.dto.request;

import java.time.LocalDateTime;

import com.spring.clinica.entity.Consulta;

public record ExameRequest(String nome, String categoria, LocalDateTime data, Consulta consulta) {

}
