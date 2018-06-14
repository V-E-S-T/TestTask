package com.web;


import com.model.User;
import com.repository.mock.InMemoryUserRepositoryImpl;
import com.util.exception.NotFoundException;
import com.web.user.UserRestController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static com.UserTestData.USER3;

@ContextConfiguration({"classpath:spring/spring-app.xml", "classpath:spring/mock.xml"})
@RunWith(SpringRunner.class)
public class InMemoryUserRestControllerSpringTest {

    @Autowired
    private UserRestController controller;

    @Autowired
    private InMemoryUserRepositoryImpl repository;

    @Before
    public void setUp() throws Exception {
        repository.init();
    }

    @Test
    public void testDelete() throws Exception {
        controller.delete(100009);
        Collection<User> users = controller.getAll();
        Assert.assertEquals(users.size(), 9);
        Assert.assertEquals(users.iterator().next(), USER3);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() throws Exception {
        controller.delete(10);
    }
}
