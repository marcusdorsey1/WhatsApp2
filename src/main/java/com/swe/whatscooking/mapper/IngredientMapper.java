package com.swe.whatscooking.mapper;

import com.swe.whatscooking.dto.IngredientDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface IngredientMapper {
    @Insert("INSERT INTO ingredient (recipe_id ,name ,quantity ,measurement)" +
            " VALUES (#{recipe_id}, #{name}, #{quantity}, #{measurement}) ")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Long insertIngredient(IngredientDTO ingredientDTO);

    @Delete("DELETE FROM ingredient WHERE recipe_id = #{recipeId}")
    void deleteIngredients(Long recipeId);

    @Select("SELECT * FROM ingredient WHERE recipe_id = #{recipeId}")
    List<IngredientDTO> selectIngredientsByID(Long recipeId);

}
