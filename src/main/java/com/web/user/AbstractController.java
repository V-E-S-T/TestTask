package com.web.user;

import com.service.UserService;
import com.util.ValidationUtil;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractController {

    @Autowired
    private UserService service;

    public List<User> getAll() {

        return service.getAll();
    }

    public User get(int id) {

        return service.get(id);
    }

    public User create(User user) {

        ValidationUtil.checkNew(user);
        return service.create(user);
    }

    public void delete(int id) {

        service.delete(id);
    }

    public void update(User user, int id) {

        ValidationUtil.assureIdConsistent(user, id);
        service.update(user);
    }
}
