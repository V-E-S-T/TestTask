package com.repository.jpa;

import com.model.User;

import java.util.List;

public interface UserRepository {

    User save(User user);
    User get(int id);
    List<User> getAll();
    boolean delete(int id);
}
