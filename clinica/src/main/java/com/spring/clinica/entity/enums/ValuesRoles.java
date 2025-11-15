package com.spring.clinica.entity.enums;

import lombok.Getter;

@Getter
public enum ValuesRoles {
    ADMIN(1L),
    BASIC(2L);

    long roleId;

    ValuesRoles(long roleId) {
        this.roleId = roleId;
    }
}
