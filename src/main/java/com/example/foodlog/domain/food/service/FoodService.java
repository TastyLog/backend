package com.example.foodlog.domain.food.service;

import com.example.foodlog.domain.food.dto.FoodSearchCondition;
import com.example.foodlog.domain.food.dto.response.ReadFoodListResponseDto;
import com.example.foodlog.domain.food.dto.response.SearchRankListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FoodService {

    Page<ReadFoodListResponseDto> readAllList(String latitude, String longitude, Pageable pageable, FoodSearchCondition foodSearchCondition);


    List<SearchRankListResponseDto> searchRankList();
}
