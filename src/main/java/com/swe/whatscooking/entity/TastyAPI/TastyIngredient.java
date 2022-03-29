package com.swe.whatscooking.entity.TastyAPI;

public class TastyIngredient {
    private String name;

    public TastyIngredient(String name) {
        this.name = name;
    }

    public TastyIngredient() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TastyIngredient{" +
                "name='" + name + '\'' +
                '}';
    }
}
