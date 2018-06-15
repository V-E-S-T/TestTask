package com.web.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.model.User;
import com.repository.jpa.JpaUserRepository;
import com.service.UserService;
import com.service.UserServiceImpl;
import com.web.json.JacksonObjectMapper;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.format.Formatter;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.sql.Date;

public class DateFormatters implements Formatter<Date> {

    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        return StringUtils.isEmpty(text) ? null : Date.valueOf(text);
    }

    @Override
    public String print(Date ld, Locale locale) {
        return ld.toString();
    }

    public static void main(String[] args) {

        try (GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext()) {
            appCtx.getEnvironment();
            appCtx.load("spring/spring-app.xml", "spring/spring-db.xml");
            appCtx.refresh();

            //System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            UserService userService = appCtx.getBean(UserService.class);
            User user = userService.get(100000);
            System.out.println(user.toString());

            System.out.println();

//            JacksonObjectMapper jacksonObjectMapper = appCtx.getBean(JacksonObjectMapper.class);
//            try {
//                System.out.println(jacksonObjectMapper.writeValueAsString(user));
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//            adminUserController.create(new User(null, "userName", "email@mail.ru", "password", Role.ROLE_ADMIN));
//            System.out.println();
//
//            MealRestController mealController = appCtx.getBean(MealRestController.class);
//            List<MealWithExceed> filteredMealsWithExceeded =
//                    mealController.getBetween(
//                            LocalDate.of(2015, Month.MAY, 30), LocalTime.of(7, 0),
//                            LocalDate.of(2015, Month.MAY, 31), LocalTime.of(11, 0));
//            filteredMealsWithExceeded.forEach(System.out::println);
        }
    }
}
