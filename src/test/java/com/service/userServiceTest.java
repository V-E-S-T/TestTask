package com.service;

import model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import service.UserService;
import util.exception.NotFoundException;

import java.sql.Date;
import java.util.List;

import static com.UserTestData.*;

public class userServiceTest extends AbstractServiceTest{

    @Autowired
    protected UserService service;

    @Test
    public void create() throws Exception {
        User newUser = new User(null, "a_newFirstName", "a_newLastName", Date.valueOf("02-11-1984"), true);
        User created = service.create(newUser);
        newUser.setId(created.getId());
        assertMatch(service.getAll(), newUser, USER9, USER3, USER7, USER8, USER2, USER4, USER5, USER0, USER6, USER1);
    }

    @Test
    public void delete() throws Exception {
        service.delete(USER9.getId());
        assertMatch(service.getAll(), USER3, USER7, USER8, USER2, USER4, USER5, USER0, USER6, USER1);
    }

    @Test(expected = NotFoundException.class)
    public void notFoundDelete() throws Exception {
        service.delete(1);
    }

    @Test
    public void get() throws Exception {
        User user = service.get(100000);
        assertMatch(user, USER0);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(1);
    }

    @Test
    public void update() throws Exception {
        User updated = new User(USER0);
        updated.setFirstName("updatedFirstName");
        updated.setLastName("updatedLastName");
        service.update(updated);
        assertMatch(service.get(100000), updated);
    }

    @Test
    public void getAll() throws Exception {
        List<User> all = service.getAll();
        assertMatch(all, USER9, USER3, USER7, USER8, USER2, USER4, USER5, USER0, USER6, USER1);
    }
}
