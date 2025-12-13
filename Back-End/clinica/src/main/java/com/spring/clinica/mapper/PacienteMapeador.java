package com.spring.clinica.mapper;

import com.spring.clinica.dto.request.PacienteRequest;
import com.spring.clinica.dto.response.PacienteResponse;
import com.spring.clinica.entity.Paciente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PacienteMapeador {

    PacienteResponse toResponse(Paciente paciente);

    Paciente toEntity(PacienteRequest request);
}
