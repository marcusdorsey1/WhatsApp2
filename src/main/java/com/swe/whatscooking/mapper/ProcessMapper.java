package com.swe.whatscooking.mapper;

import com.swe.whatscooking.dto.ProcessDTO;
import com.swe.whatscooking.entity.Process;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface ProcessMapper {
    @Insert("INSERT INTO process(recipe_id ,step , description)" +
            " VALUES (#{recipe_id}, #{step}, #{description})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Long insertProcess(ProcessDTO process);

    @Delete("DELETE FROM process WHERE recipe_id = #{recipeId}")
    void deleteProcesses(Long recipeId);
}
