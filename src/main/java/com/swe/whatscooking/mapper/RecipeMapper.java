package com.swe.whatscooking.mapper;

import com.swe.whatscooking.entity.Recipe;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RecipeMapper {
    @Insert("INSERT INTO recipe (name, cuisine, serving_size, description, image)" +
            " VALUES (#{name}, #{cuisine}, #{serving_size}, #{description}, #{image})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Long insertRecipe(Recipe recipe);

    @Delete("DELETE FROM recipe WHERE id = #{id}")
    void deleteRecipe(Long id);

    @Select("SELECT TOP 1 id FROM recipe " +
            "ORDER BY id DESC")
    Long selectLastId();

    @Select("SELECT * FROM recipe " +
            "WHERE name LIKE #{searchWord} OR cuisine LIKE #{searchWord}")
    List<Recipe> searchForRecipe(String searchWord);


}
