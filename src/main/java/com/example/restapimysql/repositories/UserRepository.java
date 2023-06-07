package com.example.restapimysql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restapimysql.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    
}
