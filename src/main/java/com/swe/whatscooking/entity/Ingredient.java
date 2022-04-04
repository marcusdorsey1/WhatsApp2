package com.swe.whatscooking.entity;

import javax.persistence.*;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private  int quantity;
    private String measurement;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public Ingredient(Long id, String name, int quantity, String measurement, Recipe recipe) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.measurement = measurement;
        this.recipe = recipe;
    }

    public Ingredient(String name, int quantity, String measurement, Recipe recipe) {
        this.name = name;
        this.quantity = quantity;
        this.measurement = measurement;
        this.recipe = recipe;
    }

    public Ingredient(String name, String measurement) {
        this.name = name;
        this.measurement = measurement;
    }

    public Ingredient() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }
}
