package com.swe.whatscooking.dto;

public class ProcessDTO {
    private Long id;
    private String description;
    private int step;
    private Long recipe_id;

    public ProcessDTO(Long id, String description, int step, Long recipe_id) {
        this.id = id;
        this.description = description;
        this.step = step;
        this.recipe_id = recipe_id;
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

    public Long getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(Long recipe_id) {
        this.recipe_id = recipe_id;
    }
}
