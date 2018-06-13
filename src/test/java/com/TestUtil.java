package com;

import org.springframework.test.web.servlet.ResultActions;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class TestUtil {


    public static String getContent(ResultActions action) throws UnsupportedEncodingException {
        return action.andReturn().getResponse().getContentAsString();
    }

    public static ResultActions print(ResultActions action) throws UnsupportedEncodingException {
        System.out.println(getContent(action));
        return action;
    }

    public static <T> T readFromJson(ResultActions action, Class<T> clazz) throws UnsupportedEncodingException {

        try
        {
            return UserTestData.MAPPER.readValue(getContent(action), clazz);
        }
        catch (IOException e)
        {
            throw new IllegalArgumentException("Invalid read from JSON:\n", e);
        }
    }
}
