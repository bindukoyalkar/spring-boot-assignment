package com.bindu.restaurant.foodorder.entity;

public class CategoryDTO {
    private int id;
    private String name;
    public CategoryDTO(){

    }
    public CategoryDTO(String name){
        this.name=name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
