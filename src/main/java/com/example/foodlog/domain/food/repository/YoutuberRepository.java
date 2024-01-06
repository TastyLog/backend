package com.example.foodlog.domain.food.repository;

import com.example.foodlog.domain.food.entity.Food;
import com.example.foodlog.domain.food.entity.Youtuber;
import com.example.foodlog.global.exception.error.YoutuberNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface YoutuberRepository extends JpaRepository<Youtuber,Long> {
}
