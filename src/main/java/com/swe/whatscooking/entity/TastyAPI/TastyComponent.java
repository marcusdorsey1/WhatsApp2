package com.swe.whatscooking.entity.TastyAPI;

import java.util.List;

public class TastyComponent {
    private String raw_text;
    private TastyIngredient ingredient;

    public TastyComponent(String raw_text, TastyIngredient ingredient) {
        this.raw_text = raw_text;
        this.ingredient = ingredient;
    }

    public TastyComponent() {
    }

    public String getRaw_text() {
        return raw_text;
    }

    public void setRaw_text(String raw_text) {
        this.raw_text = raw_text;
    }

    public TastyIngredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(TastyIngredient ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public String toString() {
        return "TastyComponent{" +
                "raw_text='" + raw_text + '\'' +
                ", ingredient=" + ingredient +
                '}';
    }
}
