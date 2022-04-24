package com.swe.whatscooking.service;

import com.swe.whatscooking.entity.Menu;
import com.swe.whatscooking.mapper.MenuMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    private MenuMapper menuMapper;

    public MenuService(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    public List<Menu> getAllMenuItems(){
        return menuMapper.getAllMenuRecords();
    }

    public Long addMenuItem(Menu menu){
        return menuMapper.insertMenu(menu);
    }
}
