package com.example.restapimysql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restapimysql.models.UserRole;

public interface UserRoleRepostiory extends JpaRepository<UserRole, String> {
    
}
