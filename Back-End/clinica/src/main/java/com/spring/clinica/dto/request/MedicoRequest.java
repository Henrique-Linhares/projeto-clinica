package com.spring.clinica.dto.request;

import java.util.List;

import com.spring.clinica.entity.Consulta;
import com.spring.clinica.entity.User;

public record MedicoRequest(User user, String especialidade, List<Consulta> consultas) {

}
