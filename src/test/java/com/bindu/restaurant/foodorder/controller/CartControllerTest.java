package com.bindu.restaurant.foodorder.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class CartControllerTest {

    @Test
    void placeOrder() {
        CartController cartController= new CartController();
        String response= cartController.placeOrder();
        Assertions.assertEquals("redirect:/cart/viewCart", response);
    }
}