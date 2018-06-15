package com;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.model.User;
import org.springframework.test.web.servlet.ResultMatcher;
import java.sql.Date;
import java.util.Arrays;

import static com.model.AbstractEntity.START_SEQ;
import static com.web.json.JacksonObjectMapper.getMapper;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class UserTestData {

    public static final User USER0 = new User(START_SEQ,        "Petro", "Sagaydak", Date.valueOf("1984-11-02"), true);   //7
    public static final User USER1 = new User(START_SEQ + 1, "Yakov", "Betrich", Date.valueOf("1974-10-03"), true);    //9
    public static final User USER2 = new User(START_SEQ + 2, "Mustafa", "Mabibulin", Date.valueOf("1988-09-04"), true);//4
    public static final User USER3 = new User(START_SEQ + 3, "Igor", "Kravec", Date.valueOf("1992-08-05"), true);      //1
    public static final User USER4 = new User(START_SEQ + 4, "Nikolette", "Faster", Date.valueOf("1991-07-06"), false);//5
    public static final User USER5 = new User(START_SEQ + 5, "Omar", "Barteluya", Date.valueOf("1984-08-07"), true);   //6
    public static final User USER6 = new User(START_SEQ + 6, "Vladislav", "Orlov", Date.valueOf("1982-09-08"), true);  //8
    public static final User USER7 = new User(START_SEQ + 7, "Katerina", "Sobol", Date.valueOf("1995-10-09"), false);   //2
    public static final User USER8 = new User(START_SEQ + 8, "Michael", "Vasyliev", Date.valueOf("1989-02-10"), true);  //3
    public static final User USER9 = new User(START_SEQ + 9, "Elena", "Gorobec", Date.valueOf("1990-11-26"), false);    //0


    public static void assertMatch(User actual, User expected) {

        assertThat(actual).isEqualToIgnoringGivenFields(expected, "birthDay");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }

    public static ResultMatcher contentJson(User... expected) {

        try {
            return content().json(getMapper().writeValueAsString(Arrays.asList(expected)));
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Invalid write to JSON:\n'" + Arrays.asList(expected) + "'", e);
        }
    }

    public static ResultMatcher contentJson(User expected) {

        try {
            return content().json(getMapper().writeValueAsString(expected));
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Invalid write to JSON:\n'" + expected + "'", e);
        }
    }
}
