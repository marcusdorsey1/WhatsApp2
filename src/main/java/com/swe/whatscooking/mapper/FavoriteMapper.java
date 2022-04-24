package com.swe.whatscooking.mapper;

import com.swe.whatscooking.entity.Favorite;
import com.swe.whatscooking.entity.Menu;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FavoriteMapper {
    @Select("SELECT * FROM favorite")
    List<Favorite> getAllFavoriteRecords();

    @Insert("INSERT INTO favorite (recipe_id , source)" +
            " VALUES (#{recipe_id}, #{source})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Long insertFavorite(Favorite favorite);
}
