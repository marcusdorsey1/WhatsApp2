package com.swe.whatscooking.entity;

import javax.persistence.*;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String ingredients;
    private String process;

    public Recipe(Long id, String name, String ingredients, String process) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.process = process;
    }

    public Recipe(String name, String ingredients, String process) {
        this.name = name;
        this.ingredients = ingredients;
        this.process = process;
    }

    public Recipe() {
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

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }
}
