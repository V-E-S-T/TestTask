package com.service;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/mock.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractServiceTest {

}
