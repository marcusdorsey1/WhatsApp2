package com.swe.whatscooking.mapper;

import com.swe.whatscooking.entity.Favorite;
import com.swe.whatscooking.entity.Menu;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FavoriteMapper {
    @Select("SELECT * FROM favorite")
    List<Favorite> getAllFavoriteRecords();

    @Insert("INSERT INTO favorite (recipe_id , source)" +
            " VALUES (#{recipe_id}, #{source})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Long insertFavorite(Favorite favorite);

    @Delete("DELETE FROM favorite WHERE recipe_id = #{recipe_id} AND source = #{source}")
    void deleteFavorite(Favorite favorite);
}
