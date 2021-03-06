package com.service;

import com.model.User;
import com.util.exception.NotFoundException;

import java.util.List;

public interface UserService {

    User create(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    List<User> getAll();

    void update(User user);

}
