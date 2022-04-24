package com.swe.whatscooking.dto;

import com.swe.whatscooking.entity.Ingredient;
import com.swe.whatscooking.entity.Process;
import com.swe.whatscooking.entity.TastyAPI.TastyInstruction;
import com.swe.whatscooking.entity.TastyAPI.TastySection;

import java.util.List;
// TODO: Need to update once we combine both our DB and Recipe API
public class RecipeDTO {
    private Long id;
    private String name;
    private String cuisine;
    private Integer serving_size;
    private String description;
    private String image;
    private List<Ingredient> ingredients;
    private List<Process> processes;
    private int num_servings;
    private String thumbnail_url;
    private List<TastySection> sections;
    private List<TastyInstruction> instructions;
    private String source;

    public RecipeDTO() {
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
        return getNum_servings();
        //return serving_size;
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
        if (source.equals("TastyAPI")) {
            return getThumbnail_url();
        }
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

    public int getNum_servings() {
        return num_servings;
    }

    public void setNum_servings(int num_servings) {
        this.num_servings = num_servings;
    }

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }

    public List<TastySection> getSections() {
        return sections;
    }

    public void setSections(List<TastySection> sections) {
        this.sections = sections;
    }

    public List<TastyInstruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<TastyInstruction> instructions) {
        this.instructions = instructions;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
