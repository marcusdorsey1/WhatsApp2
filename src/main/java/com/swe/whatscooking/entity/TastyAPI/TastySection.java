package com.swe.whatscooking.entity.TastyAPI;

import java.util.List;

public class TastySection {
    private String name;
    private List<TastyComponent> components;

    public TastySection(String name, List<TastyComponent> components) {
        this.name = name;
        this.components = components;
    }

    public TastySection() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TastyComponent> getComponents() {
        return components;
    }

    public void setComponents(List<TastyComponent> components) {
        this.components = components;
    }

    @Override
    public String toString() {
        return "TastySection{" +
                "name='" + name + '\'' +
                ", components=" + components +
                '}';
    }
}
