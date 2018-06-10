package web.controller;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import service.UserService;

import static util.ValidationUtil.assureIdConsistent;
import static util.ValidationUtil.checkNew;

public abstract class AbstractController {

    @Autowired
    private UserService service;



    public User get(int id) {

        return service.get(id);
    }

    public User create(User user) {

        checkNew(user);
        return service.create(user);
    }

    public void delete(int id) {

        service.delete(id);
    }

    public void update(User user, int id) {

        assureIdConsistent(user, id);
        service.update(user);
    }
}
