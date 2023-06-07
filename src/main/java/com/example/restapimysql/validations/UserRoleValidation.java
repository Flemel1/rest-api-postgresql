package com.example.restapimysql.validations;

import jakarta.validation.constraints.NotNull;

public class UserRoleValidation {
    @NotNull(message = "Role Name Must Be Fill")
    private String role_name;

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }
}
