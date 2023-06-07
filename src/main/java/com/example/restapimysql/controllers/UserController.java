package com.example.restapimysql.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerErrorException;

import com.example.restapimysql.models.User;
import com.example.restapimysql.repositories.UserRepository;
import com.example.restapimysql.services.UserServiceImpl;
import com.example.restapimysql.validations.UserValidation;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {
    @Autowired
    UserServiceImpl service;
    @Autowired
    private UserRepository repository;

    @GetMapping
    public ResponseEntity<Object> getUsers(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "asc") String sort) {
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            List<User> users = new ArrayList<User>();
            PageRequest paging;
            if (sort.equals("asc")) {
                paging = PageRequest.of(page - 1, 5, Sort.by("name").ascending());
            } else {
                paging = PageRequest.of(page - 1, 5, Sort.by("name").descending());
            }
            Page<User> pages = repository.findAll(paging);
            users = pages.getContent();
            response.put("status_code", 200);
            response.put("data", users);
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

    @PostMapping
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserValidation validation,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<String, String>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }

            return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
        }
        Map<String, Object> response = new HashMap<String, Object>();
        User entity = new User();
        entity.setEmail(validation.getEmail());
        entity.setName(validation.getName());
        entity.setBirthday(validation.getBirthday());
        entity.setAddress(validation.getAddress());
        entity.setName(validation.getName());
        entity.setSex(validation.getSex());
        entity.setRoleName(validation.getRole_name());
        try {
            repository.save(entity);
            response.put("status_code", 200);
            response.put("message", "user created");
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            response.put("status_code", 500);
            response.put("message", "data not valid");
            return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> showUser(@PathVariable("id") int id) {
        try {
            Map<String, Object> response = new HashMap<String, Object>();
            User user = repository.findById(id).get();
            response.put("status_code", 200);
            response.put("data", user);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            Map<String, Object> response = new HashMap<String, Object>();
            response.put("status_code", 404);
            response.put("message", "user not found");
            return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
        } catch (NoSuchElementException e) {
            Map<String, Object> response = new HashMap<String, Object>();
            response.put("status_code", 404);
            response.put("message", "user not found");
            return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> updateUser(@Valid @RequestBody UserValidation validation, @PathVariable Integer id,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<String, String>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }

            return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
        }
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            User entity = repository.findById(id).get();
            entity.setEmail(validation.getEmail());
            entity.setName(validation.getName());
            entity.setBirthday(validation.getBirthday());
            entity.setAddress(validation.getAddress());
            entity.setName(validation.getName());
            entity.setSex(validation.getSex());
            entity.setRoleName(validation.getRole_name());
            try {
                repository.save(entity);
                response.put("status_code", 200);
                response.put("message", "user updated");
                return new ResponseEntity<Object>(response, HttpStatus.OK);
            } catch (DataIntegrityViolationException e) {
                response.put("status_code", 500);
                response.put("message", "error constraint");
                return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            } catch (Exception e) {
                response.put("status_code", 500);
                response.put("message", "message: " + e.getMessage() + " class: " + e.getClass());
                return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (NoSuchElementException e) {
            response.put("status_code", 404);
            response.put("message", "data not found");
            return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            response.put("status_code", 500);
            response.put("message", "message: " + e.getMessage() + " class: " + e.getClass());
            return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") int id) {
        try {
            Map<String, Object> response = new HashMap<String, Object>();
            User user = repository.findById(id).get();
            repository.delete(user);
            response.put("status_code", 200);
            response.put("message", "user deleted");
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            Map<String, Object> response = new HashMap<String, Object>();
            response.put("status_code", 404);
            response.put("message", "user not found");
            return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
        } catch (NoSuchElementException e) {
            Map<String, Object> response = new HashMap<String, Object>();
            response.put("status_code", 404);
            response.put("message", "user not found");
            return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
        }
    }
}
