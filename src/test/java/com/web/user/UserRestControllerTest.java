package com.web.user;

import com.TestUtil;
import com.model.User;
import com.web.AbstractControllerTest;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.sql.Date;
import java.util.Arrays;

import static com.TestUtil.readFromJson;
import static com.UserTestData.*;
import static com.web.json.JacksonObjectMapper.getMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserRestControllerTest extends AbstractControllerTest{

    private static final String REST_URL = UserRestController.REST_URL + '/';

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + 100000))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(USER0));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + 100000))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertMatch(userService.getAll(), USER9, USER3, USER7, USER8, USER2, USER4, USER5, USER6, USER1);
    }

    @Test
    public void testUpdate() throws Exception {
        User updated = new User(USER0);
        updated.setFirstName("updatedFirstName");
        updated.setLastName("updatedLastName");
        mockMvc.perform(put(REST_URL + 100000)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getMapper().writeValueAsString(updated)))
                .andExpect(status().isOk());

        System.out.println("updated : " + updated);
        System.out.println("expected : " + userService.get(100000));
        assertMatch(userService.get(100000), updated);
    }

    @Test
    public void testCreate() throws Exception {
        User expected = new User(null, "a_newFirstName", "a_newLastName", Date.valueOf("1984-11-02"), true);
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getMapper().writeValueAsString(expected)))
                .andExpect(status().isCreated());

        User returned = readFromJson(action, User.class);
        expected.setId(returned.getId());

        assertMatch(returned, expected);
        assertMatch(userService.getAll(), expected, USER9, USER3, USER7, USER8, USER2, USER4, USER5, USER0, USER6, USER1);

        User [] expectedUsers = {expected, USER9, USER3, USER7, USER8, USER2, USER4, USER5, USER0, USER6, USER1};

        System.out.println("expectedUsers : " + Arrays.asList(expectedUsers).toString());
        System.out.println("getUsers : " + userService.getAll().toString());
    }

    @Test
    public void testGetAll() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(USER9, USER3, USER7, USER8, USER2, USER4, USER5, USER0, USER6, USER1)));
    }
}
