package com.swe.whatscooking.service;

import com.swe.whatscooking.entity.Favorite;
import com.swe.whatscooking.entity.Menu;
import com.swe.whatscooking.mapper.FavoriteMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {
    private FavoriteMapper favoriteMapper;

    public FavoriteService(FavoriteMapper favoriteMapper) {
        this.favoriteMapper = favoriteMapper;
    }

    public List<Favorite> getAllFavoriteItems(){
        return favoriteMapper.getAllFavoriteRecords();
    }

    public Long addFavoriteRecipe(Favorite favorite){
        return favoriteMapper.insertFavorite(favorite);
    }

    public void removeFavoriteItem(Favorite favorite){
        favoriteMapper.deleteFavorite(favorite);
    }
}
