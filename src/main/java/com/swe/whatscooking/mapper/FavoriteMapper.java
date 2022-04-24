package com.swe.whatscooking.mapper;

import com.swe.whatscooking.entity.Favorite;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FavoriteMapper {
    @Select("SELECT * FROM favorite")
    List<Favorite> getAllFavoriteRecords();
}
