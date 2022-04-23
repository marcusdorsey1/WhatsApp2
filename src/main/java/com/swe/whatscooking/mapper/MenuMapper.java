package com.swe.whatscooking.mapper;

import com.swe.whatscooking.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MenuMapper {
    @Select("SELECT * FROM menu")
    List<Menu> getAllMenuRecords();
}
