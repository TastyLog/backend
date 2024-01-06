package com.example.foodlog.domain.food.service.impl;


import com.example.foodlog.domain.food.dto.response.ReadFoodListResponseDto;
import com.example.foodlog.domain.food.dto.response.ReadYoutuberListResponseDto;
import com.example.foodlog.domain.food.entity.Food;
import com.example.foodlog.domain.food.entity.Youtuber;
import com.example.foodlog.domain.food.repository.FoodRepository;
import com.example.foodlog.domain.food.repository.YoutuberRepository;
import com.example.foodlog.domain.food.service.YoutuberService;
import com.example.foodlog.global.exception.error.YoutuberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class YoutuberSerivceImpl implements YoutuberService {

    private final YoutuberRepository youtuberRepository;

    @Override
    public List<ReadYoutuberListResponseDto> readAllList() {
        List<Youtuber> youtuberList = youtuberRepository.findAll();
        List<ReadYoutuberListResponseDto> result = youtuberList.stream().
                map(ReadYoutuberListResponseDto::new).collect(Collectors.toList());

        return result;
    }


}
