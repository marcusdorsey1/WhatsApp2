package com.swe.whatscooking.entity.TastyAPI;

import java.util.List;

public class TastyRecipeSearch {
    private Integer count;
    private List<TastyRecipe> results;

    public TastyRecipeSearch(Integer count, List<TastyRecipe> results) {
        this.count = count;
        this.results = results;
    }

    public TastyRecipeSearch() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<TastyRecipe> getResults() {
        return results;
    }

    public void setResults(List<TastyRecipe> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "TastyRecipeSearch{" +
                "count=" + count +
                ", results=" + results +
                '}';
    }
}
