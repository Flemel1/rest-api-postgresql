package com.example.restapimysql.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restapimysql.models.User;
import com.example.restapimysql.repositories.UserRepository;
import com.example.restapimysql.services.base.RestfulServiceBase;

@Service
public class UserServiceImpl implements RestfulServiceBase {

    @Autowired
    UserRepository repository;

    @Override
    public List<User> fetchAll() {
        return repository.findAll();
    }

    @Override
    public <T> T update(T id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Boolean delete(Integer id) {
        try {
            User user = repository.findById(id).get();
            repository.delete(user);
            return true;
        } catch (NoSuchElementException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public <T> T create(T model) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public <T> T show(T id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'show'");
    }

}
