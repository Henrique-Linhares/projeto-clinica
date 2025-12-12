package com.spring.clinica.mapper;

import com.spring.clinica.dto.request.RoleRequest;
import com.spring.clinica.dto.response.RoleResponse;
import com.spring.clinica.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleResponse toResponse (Role role);

    Role toEntity (RoleRequest request);
}
