package com.spring.clinica.mapper;

import com.spring.clinica.dto.request.ExameRequest;
import com.spring.clinica.dto.response.ExameResponse;
import com.spring.clinica.entity.Exame;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExameMapper {

    ExameResponse toResponse(Exame exame);

    Exame toEntity(ExameRequest request);
}
