package com.example.restapimysql.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerErrorException;

import com.example.restapimysql.models.UserRole;
import com.example.restapimysql.repositories.UserRoleRepostiory;
import com.example.restapimysql.validations.UserRoleValidation;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/roles")
@CrossOrigin
public class UserRoleController {
    @Autowired
    private UserRoleRepostiory repository;

    @GetMapping
    public ResponseEntity<Object> getUserRoles(@RequestParam(defaultValue = "1") int page) {
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            List<UserRole> roles = new ArrayList<UserRole>();
            PageRequest paging = PageRequest.of(page - 1, 5);
            Page<UserRole> pages = repository.findAll(paging);
            roles = pages.getContent();
            response.put("status_code", 200);
            response.put("data", roles);
            response.put("current_page", pages.getNumber() + 1);
            response.put("total_items", pages.getTotalElements());
            response.put("total_pages", pages.getTotalPages());
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        } catch (ServerErrorException e) {
            response.put("status_code", 500);
            response.put("message", "server error");
            return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            response.put("status_code", 500);
            response.put("message", e.getMessage());
            return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public Iterable<UserRole> fetchAllRole() {
        return repository.findAll();

    }

    @PostMapping
    public ResponseEntity<Object> createRole(@Valid @RequestBody UserRoleValidation validation,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<String, String>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }

            return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
        }
        Map<String, Object> response = new HashMap<String, Object>();
        UserRole role = new UserRole();
        role.setRole_name(validation.getRole_name());
        repository.save(role);
        response.put("status_code", 200);
        response.put("message", "role created");
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable String name) {
        Map<String, Object> response = new HashMap<>();
        try {
            UserRole role = repository.findById(name).get();
            repository.delete(role);
            response.put("status_code", 200);
            response.put("message", "data deleted");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            response.put("status_code", 404);
            response.put("message", "data not found");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            response.put("status_code", 500);
            response.put("message", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
