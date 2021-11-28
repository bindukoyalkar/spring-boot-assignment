package com.bindu.restaurant.foodorder.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class AdminControllerTest {

    @Test
    void adminHome() {
        AdminController adminController = new AdminController();
        String response = adminController.adminHome();
        Assertions.assertEquals("adminHome",response);
    }

   /* @Test
    void addDish(){
        Model model = null;
        AdminController adminController = new AdminController();
        String response = adminController.addDish(model);
        Assertions.assertEquals("addOrUpdateDish",response);
    }*/

}