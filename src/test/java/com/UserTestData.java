package com;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.User;
import org.springframework.test.web.servlet.ResultMatcher;

import java.sql.Date;
import java.util.Arrays;

import static model.AbstractEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class UserTestData {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    //Test Data
    public static final User USER0 = new User(START_SEQ,        "Petro", "Sagaydak", Date.valueOf("02-11-1984"), true);   //7
    public static final User USER1 = new User(START_SEQ + 1, "Yakov", "Betrich", Date.valueOf("03-10-1974"), true);    //9
    public static final User USER2 = new User(START_SEQ + 2, "Mustafa", "Mabibulin", Date.valueOf("04-09-1988"), true);//4
    public static final User USER3 = new User(START_SEQ + 3, "Igor", "Kravec", Date.valueOf("05-08-1992"), true);      //1
    public static final User USER4 = new User(START_SEQ + 4, "Nikolette", "Faster", Date.valueOf("06-07-1991"), false);//5
    public static final User USER5 = new User(START_SEQ + 5, "Omar", "Barteluya", Date.valueOf("07-08-1984"), true);   //6
    public static final User USER6 = new User(START_SEQ + 6, "Vladislav", "Orlov", Date.valueOf("08-09-1982"), true);  //8
    public static final User USER7 = new User(START_SEQ + 7, "Katerina", "Sobol", Date.valueOf("09-10-1995"), false);   //2
    public static final User USER8 = new User(START_SEQ + 8, "Michael", "Vasyliev", Date.valueOf("10-02-1989"), true);  //3
    public static final User USER9 = new User(START_SEQ + 9, "Elena", "Gorobec", Date.valueOf("26-11-1990"), false);    //0


    public static void assertMatch(User actual, User expected) {
        //assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "meals");
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }


    // getAll
    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "meals").isEqualTo(expected);
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
