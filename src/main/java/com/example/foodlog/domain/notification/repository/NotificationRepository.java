package com.example.foodlog.domain.notification.repository;

import com.example.foodlog.domain.food.entity.Food;
import com.example.foodlog.domain.food.repository.querydsl.FoodQuerydslRepository;
import com.example.foodlog.domain.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Long>{
}
