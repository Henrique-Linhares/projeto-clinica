package com.spring.clinica.mapper;

import com.spring.clinica.dto.request.MedicoRequest;
import com.spring.clinica.dto.response.MedicoResponse;
import com.spring.clinica.entity.Medico;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedicoMapper {

    MedicoResponse toResponse(Medico medico);

    Medico toEntity(MedicoRequest request);
}
