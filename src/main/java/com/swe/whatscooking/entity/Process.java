package com.swe.whatscooking.entity;

import javax.persistence.*;

@Entity
public class Process {
    @Id
    @GeneratedValue
    private Long id;

    private String description;
    private int step;
    //private long recipe_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public Process(Long id, String description, int step, Recipe recipe) {
        this.id = id;
        this.description = description;
        this.step = step;
        this.recipe = recipe;
    }

    public Process(String description, int step, Recipe recipe) {
        this.description = description;
        this.step = step;
        this.recipe = recipe;
    }

    public Process() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }
}
