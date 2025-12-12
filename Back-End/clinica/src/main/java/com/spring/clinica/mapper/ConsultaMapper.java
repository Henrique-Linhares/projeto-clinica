package com.spring.clinica.mapper;

import com.spring.clinica.dto.request.ConsultaRequest;
import com.spring.clinica.dto.response.ConsultaResponse;
import com.spring.clinica.entity.Consulta;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConsultaMapper {

    ConsultaResponse toResponse (Consulta consulta);

    Consulta toEntity(ConsultaRequest request);
}
