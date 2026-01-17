package com.spring.clinica.dto.response;

import java.util.List;

public record MedicoResponse(Long id, String loginUsuario, String especialidade, List<String> consultasInfo) {
    
}
