package com.swe.whatscooking.entity.TastyAPI;

import java.util.List;

public class TastyRecipe {
    private Long id;
    private String description;
    private int num_servings;
    private String name;
    private String thumbnail_url;
    private List<TastySection> sections;
    private List<TastyInstruction> instructions;

    public TastyRecipe(Long id, String description, int num_servings, String name, String thumbnail_url, List<TastySection> sections, List<TastyInstruction> instructions) {
        this.id = id;
        this.description = description;
        this.num_servings = num_servings;
        this.name = name;
        this.thumbnail_url = thumbnail_url;
        this.sections = sections;
        this.instructions = instructions;
    }

    public TastyRecipe() {
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

    public int getNum_servings() {
        return num_servings;
    }

    public void setNum_servings(int num_servings) {
        this.num_servings = num_servings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "TastyRecipe{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", num_servings=" + num_servings +
                ", name='" + name + '\'' +
                ", thumbnail_url='" + thumbnail_url + '\'' +
                ", sections=" + sections +
                ", instructions=" + instructions +
                '}';
    }
}
