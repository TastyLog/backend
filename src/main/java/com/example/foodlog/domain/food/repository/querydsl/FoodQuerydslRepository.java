package com.example.foodlog.domain.food.repository.querydsl;

import com.example.foodlog.domain.food.entity.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FoodQuerydslRepository {
    Page<Food> readFoodList(Pageable pageable);
}
