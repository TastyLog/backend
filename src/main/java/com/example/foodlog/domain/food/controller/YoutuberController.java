package com.example.foodlog.domain.food.controller;

import com.example.foodlog.domain.food.dto.response.ReadFoodListResponseDto;
import com.example.foodlog.domain.food.dto.response.ReadYoutuberListResponseDto;
import com.example.foodlog.domain.food.service.YoutuberService;
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

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/youtuber")
public class YoutuberController {

    private final YoutuberService youtuberService;

    @GetMapping("/all-list")
    public ResponseEntity<CommonResDto<?>> readAllList(){

        List<ReadYoutuberListResponseDto> result = youtuberService.readAllList();
        return ResponseEntity.status(HttpStatus.OK).body(new CommonResDto<>(1,"youtuberList 조회 성공",result));
    }


}
