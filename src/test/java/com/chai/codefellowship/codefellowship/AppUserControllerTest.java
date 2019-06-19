package com.chai.codefellowship.codefellowship;

import org.junit.Test;

import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AppUserControllerTest {



    @Autowired
    AppUserController AppUserController;

    @Autowired
    MockMvc mockMvc;


    @Test
    public void test_ControllerIsAutowired() {
        assertNotNull(AppUserController);
    }

    @Test
    public void test_Request_login() throws Exception {
        mockMvc.perform(get("/login")).andExpect(content().string(containsString("Log in")));
    }
    @Test
    public void test_Request_home() throws Exception {
        mockMvc.perform(get("/")).andExpect(content().string(containsString("")));
    }

    @Test
    public void test_Request_singup() throws Exception {
        mockMvc.perform(get("singup")).andExpect(content().string(containsString("")));
    }
}