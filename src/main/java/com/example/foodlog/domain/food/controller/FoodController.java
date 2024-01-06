package com.example.foodlog.domain.food.controller;


import com.example.foodlog.domain.food.dto.FoodSearchCondition;
import com.example.foodlog.domain.food.dto.response.ReadFoodListResponseDto;
import com.example.foodlog.domain.food.service.FoodService;
import com.example.foodlog.global.common.dto.CommonResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/food")
public class FoodController {

    private final FoodService foodService;

    @GetMapping("/{latitube}/{longtitude}")
    public ResponseEntity<CommonResDto<?>> readAllList(
                                                        FoodSearchCondition foodSearchCondition,
                                                        @PathVariable(name="latitube") String latitube,
                                                        @PathVariable(name="longtitude") String longtitude,
                                                        Pageable pageable){
        Page<ReadFoodListResponseDto> result = foodService.readAllList(latitube, longtitude, pageable,foodSearchCondition);
        return ResponseEntity.status(HttpStatus.OK).body(new CommonResDto<>(1,"foodList동적조회 성공",result));
    }

}
