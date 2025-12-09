package com.spring.clinica.mapper;

import org.mapstruct.Mapper;

import com.spring.clinica.dto.request.UserRequest;
import com.spring.clinica.dto.response.UserResponse;
import com.spring.clinica.entity.User;

@Mapper(componentModel = "spring") 
public interface UserMapper {

    UserResponse toResponse(User user); 

    User toEntity(UserRequest userRequest);
    
}