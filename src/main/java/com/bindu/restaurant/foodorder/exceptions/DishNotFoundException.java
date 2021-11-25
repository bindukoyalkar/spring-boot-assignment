package com.bindu.restaurant.foodorder.exceptions;

public class DishNotFoundException extends RuntimeException{
    public DishNotFoundException(String message) {
        super(message);
    }
}
