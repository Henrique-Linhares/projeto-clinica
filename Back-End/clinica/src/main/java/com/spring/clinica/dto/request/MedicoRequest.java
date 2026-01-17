package com.spring.clinica.dto.request;

import java.util.List;


public record MedicoRequest(String especialidade, List<ConsultaRequest> consultas) {

}
