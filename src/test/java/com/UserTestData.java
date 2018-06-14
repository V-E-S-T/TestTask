package com;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.User;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.*;
import java.util.Arrays;

import static com.model.AbstractEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class UserTestData {

    public static final ObjectMapper MAPPER = new ObjectMapper();

    //LocalDate.of(1986, 8, 15)

    //Test Data
    public static final User USER0 = new User(START_SEQ,        "Petro", "Sagaydak", new Date(1986, 8, 11), true);   //7
    public static final User USER1 = new User(START_SEQ + 1, "Yakov", "Betrich", new Date(1987, 9, 2), true);    //9
    public static final User USER2 = new User(START_SEQ + 2, "Mustafa", "Mabibulin", new Date(1988, 10, 5), true);//4
    public static final User USER3 = new User(START_SEQ + 3, "Igor", "Kravec", new Date(1989, 11, 15), true);      //1
    public static final User USER4 = new User(START_SEQ + 4, "Nikolette", "Faster", new Date(1981, 7, 11), false);//5
    public static final User USER5 = new User(START_SEQ + 5, "Omar", "Barteluya", new Date(1986, 6, 11), true);   //6
    public static final User USER6 = new User(START_SEQ + 6, "Vladislav", "Orlov", new Date(1982, 6, 11), true);  //8
    public static final User USER7 = new User(START_SEQ + 7, "Katerina", "Sobol", new Date(1983, 5, 11), false);   //2
    public static final User USER8 = new User(START_SEQ + 8, "Michael", "Vasyliev", new Date(1986, 7, 11), true);  //3
    public static final User USER9 = new User(START_SEQ + 9, "Elena", "Gorobec", new Date(1986, 4, 11), false);    //0


    public static void assertMatch(User actual, User expected) {
        //assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "meals");
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }


    // getAll
    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }

    public static ResultMatcher contentJson(User... expected) {

        try {
            return content().json(MAPPER.writeValueAsString(Arrays.asList(expected)));
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Invalid write to JSON:\n'" + Arrays.asList(expected) + "'", e);
        }
    }

    public static ResultMatcher contentJson(User expected) {

        try {
            return content().json(MAPPER.writeValueAsString(expected));
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Invalid write to JSON:\n'" + expected + "'", e);
        }
    }
}
