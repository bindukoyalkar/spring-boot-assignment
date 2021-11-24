package com.bindu.restaurant.foodorder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="category_id")
    private int id;

    @Column(name = "category_name")
    @NotEmpty
    @Size(min=2, max=30, message = "category name should have at-least 2 characters")
    private String name;
}
