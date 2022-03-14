package com.swe.whatscooking.repository;

import com.swe.whatscooking.entity.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
