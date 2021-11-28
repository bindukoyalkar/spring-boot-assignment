package com.bindu.restaurant.foodorder.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class LoginControllerTest {

    @Test
    void login() {
        LoginController loginController=new LoginController();
        String response=loginController.login();
        Assertions.assertEquals("login",response);
    }
}