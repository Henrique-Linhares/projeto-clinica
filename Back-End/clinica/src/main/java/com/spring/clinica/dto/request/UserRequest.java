package com.spring.clinica.dto.request;


import com.spring.clinica.entity.enums.UserRole;

public record UserRequest(String login, String senha, UserRole role) {

}
