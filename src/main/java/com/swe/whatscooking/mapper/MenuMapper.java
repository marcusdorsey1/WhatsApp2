package com.swe.whatscooking.mapper;

import com.swe.whatscooking.dto.ProcessDTO;
import com.swe.whatscooking.entity.Menu;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MenuMapper {
    @Select("SELECT * FROM menu")
    List<Menu> getAllMenuRecords();

    @Insert("INSERT INTO menu (recipe_id , source)" +
            " VALUES (#{recipe_id}, #{source})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Long insertMenu(Menu menu);

    @Delete("DELETE FROM menu WHERE recipe_id = #{recipe_id} AND source = #{source}")
    void deleteMenu(Menu menu);
}
