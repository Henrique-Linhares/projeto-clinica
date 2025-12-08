package com.spring.clinica.dto.request;

import java.util.Set;

import com.spring.clinica.entity.Role;

public record UserRequest(String email, String senha, Set<Role> roles) {

}
