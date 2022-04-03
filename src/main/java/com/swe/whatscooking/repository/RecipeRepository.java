package com.swe.whatscooking.repository;

import com.swe.whatscooking.entity.Recipe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    @Query("select r from Recipe r where r.name=:name")
    List<Recipe> findByName(String name);

    @Query("select r from Recipe r where r.cuisine=:cuisine")
    List<Recipe> findByCuisine(String cuisine);
}
