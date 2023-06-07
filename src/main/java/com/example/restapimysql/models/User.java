package com.example.restapimysql.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;

@Entity(name = "tb_master_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nama", nullable = false)
    private String name;
    @Column(name = "jenis_kelamin", nullable = false)
    private String sex;
    @Column(name = "tgl_lahir", columnDefinition = "TIMESTAMP DEFAULT current_timestamp", nullable = false)
    private LocalDate birthday;
    @Column(name = "alamat", nullable = false)
    private String address;
    @Column(nullable = false)
    @Email(message = "email is already used")
    private String email;
    @Column(name = "role_name", nullable = false)
    private String role_name;
    @ManyToOne
    @JoinColumn(name = "role_name", nullable = false, insertable = false, updatable = false)
    private UserRole role;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        
        return sex;
    }
    public void setRoleName(String roleName) {
        this.role_name = roleName;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public LocalDate getBirthday() {
        return birthday;
    }
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public UserRole getRole() {
        return role;
    }
    public void setRole(UserRole role) {
        this.role = role;
    }

    
}
