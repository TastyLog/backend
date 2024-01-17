package com.example.foodlog.domain.food.repository.querydsl;

import com.example.foodlog.domain.food.dto.FoodSearchCondition;
import com.example.foodlog.domain.food.entity.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FoodQuerydslRepository {
    Page<Food> readFoodList(Pageable pageable, FoodSearchCondition foodSearchCondition);
    Page<Food> readClosestFoodList(Pageable pageable, FoodSearchCondition foodSearchCondition,String latitude, String longitude);
}
