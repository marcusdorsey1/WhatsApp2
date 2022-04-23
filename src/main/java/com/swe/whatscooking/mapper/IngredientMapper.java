package com.swe.whatscooking.mapper;

import com.swe.whatscooking.dto.IngredientDTO;
import com.swe.whatscooking.entity.Ingredient;
import com.swe.whatscooking.entity.Recipe;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface IngredientMapper {
    @Insert("INSERT INTO ingredient (recipe_id ,name ,quantity ,measurement)" +
            " VALUES (#{recipe_id}, #{name}, #{quantity}, #{measurement}) ")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Long insertIngredient(IngredientDTO ingredientDTO);

    @Delete("DELETE FROM ingredient WHERE recipe_id = #{recipeId}")
    void deleteIngredients(Long recipeId);
}
