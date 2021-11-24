package com.bindu.restaurant.foodorder.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Entity
@Table(name = "menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dish_id")
    private int dishId;

    @NotEmpty
    @Size(min=2, max=30)
    @Column(name = "dish_name")
    private String dishName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id" , referencedColumnName = "category_id")
    private Category category;

    @NotNull
    @Min(0)
    private double price;

    @NotNull
    @Min(0)
    private int quantity;

    private String description;

    public Menu(int dishId, String dishName, double price, int quantity, String description) {
        this.dishId = dishId;
        this.dishName = dishName;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }
}
