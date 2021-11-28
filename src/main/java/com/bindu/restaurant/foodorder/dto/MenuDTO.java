package com.bindu.restaurant.foodorder.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class MenuDTO {

    private int dishId;

    @NotEmpty
    @Size(min=2, max=30,message ="dish name should have at-least 2 characters")
    private String dishName;

    private int categoryId;

    @NotNull
    @Min(value = 50,message = "price should be greater than 50")
    private double price;

    @NotNull
    @Min(value = 0,message = "quantity should be greater than 0")
    private int quantity;

    private String description;
}
