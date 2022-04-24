package com.swe.whatscooking.mapper;

import com.swe.whatscooking.dto.ProcessDTO;
import com.swe.whatscooking.entity.Menu;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MenuMapper {
    @Select("SELECT * FROM menu")
    List<Menu> getAllMenuRecords();

    @Insert("INSERT INTO menu (recipe_id , source)" +
            " VALUES (#{recipe_id}, #{source})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Long insertMenu(Menu menu);
}
