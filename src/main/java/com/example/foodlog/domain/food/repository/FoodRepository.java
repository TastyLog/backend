package com.example.foodlog.domain.food.repository;

import com.example.foodlog.domain.food.entity.Food;
import com.example.foodlog.domain.food.repository.querydsl.FoodQuerydslRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food,Long> , FoodQuerydslRepository {


}
