package com.swe.whatscooking.mapper;

import com.swe.whatscooking.entity.Recipe;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface RecipeMapper {
    @Insert("INSERT INTO recipe (id, name, cuisine, serving_size, description, image)" +
            " VALUES (#{id}, #{name}, #{cuisine}, #{serving_size}, #{description}, #{image}) ")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertRecipe(Recipe recipe);
}
