package com.swe.whatscooking.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String cuisine;
    private Integer serving_size;
    private String description;
    private String image;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recipe")
    private List<Ingredient> ingredients;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<Process> processes;

    public Recipe(Long id, String name, String cuisine, Integer serving_size, String description, String image, List<Ingredient> ingredients, List<Process> processes) {
        this.id = id;
        this.name = name;
        this.cuisine = cuisine;
        this.serving_size = serving_size;
        this.description = description;
        this.image = image;
        this.ingredients = ingredients;
        this.processes = processes;
    }

    public Recipe(String name, String cuisine, Integer serving_size, String description, String image, List<Ingredient> ingredients, List<Process> processes) {
        this.name = name;
        this.cuisine = cuisine;
        this.serving_size = serving_size;
        this.description = description;
        this.image = image;
        this.ingredients = ingredients;
        this.processes = processes;
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

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public Integer getServing_size() {
        return serving_size;
    }

    public void setServing_size(Integer serving_size) {
        this.serving_size = serving_size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Process> getProcesses() {
        return processes;
    }

    public void setProcesses(List<Process> processes) {
        this.processes = processes;
    }
}
