package com.example.foodlog.domain.food.repository;

import com.example.foodlog.domain.food.entity.Food;
import com.example.foodlog.domain.food.repository.querydsl.FoodQuerydslRepository;
import com.example.foodlog.global.exception.error.FoodNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food,Long> , FoodQuerydslRepository {

}
