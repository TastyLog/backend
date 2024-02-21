package com.example.foodlog.domain.food.service.impl;

import com.example.foodlog.domain.food.dto.FoodSearchCondition;
import com.example.foodlog.domain.food.dto.response.ReadFoodListResponseDto;
import com.example.foodlog.domain.food.dto.response.SearchRankListResponseDto;
import com.example.foodlog.domain.food.entity.Food;
import com.example.foodlog.domain.food.repository.FoodRepository;
import com.example.foodlog.domain.food.service.FoodService;
import com.example.foodlog.global.redis.util.SearchRedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;
    private final SearchRedisUtil searchRedisUtil;

    @Override
    public Page<ReadFoodListResponseDto> readAllList(String latitude, String longitude, Pageable pageable, FoodSearchCondition foodSearchCondition) {


        if(foodSearchCondition.getSearchWord()!=null && !foodSearchCondition.getSearchWord().isEmpty()){
            searchRedisUtil.incrementSearchKeywordScore(foodSearchCondition.getSearchWord());
        }
        if(pageable.getSort()==Sort.unsorted()){
            Page<Food> foods = foodRepository.readClosestFoodList(pageable,foodSearchCondition,latitude,longitude);
            return getFoodListUnsorted(latitude, longitude, pageable, foods);
        }else{
            Page<Food> foods = foodRepository.readFoodList(pageable,foodSearchCondition);
            return getFoodListSorted(latitude, longitude, pageable, foods);
        }
    }

    @Override
    public List<SearchRankListResponseDto> searchRankList() {
        String key = "ranking";
        Set<ZSetOperations.TypedTuple<String>> top10PopularKeywords = searchRedisUtil.getTop10PopularKeywords(key);


        // 순위(rank) 추가
        AtomicLong rank = new AtomicLong(1);
        List<SearchRankListResponseDto> result = top10PopularKeywords.stream()
                .map(tuple -> {
                    String value = tuple.getValue();
                    Double score = tuple.getScore();
                    long currentRank = rank.getAndIncrement();
                    return new SearchRankListResponseDto(currentRank, value, score);
                })
                .collect(Collectors.toList());
        return result;
    }

    private static PageImpl<ReadFoodListResponseDto> getFoodListUnsorted(String latitude, String longitude, Pageable pageable, Page<Food> foods) {
        List<ReadFoodListResponseDto> result = foods.stream()
                .map(food -> {
                    double distanceDiff = distance(
                            Double.parseDouble(latitude),
                            Double.parseDouble(longitude),
                            Double.parseDouble(food.getLatitude()),
                            Double.parseDouble(food.getLongtitude()),
                            "M");

                    return Map.entry(food, distanceDiff);
                })
                .sorted(Comparator.comparingDouble(Map.Entry::getValue))
                .map(entry -> ReadFoodListResponseDto.builder()
                        .food(entry.getKey())
                        .distance(entry.getValue())
                        .build())
                .collect(Collectors.toList());


        return new PageImpl<>(result, pageable, foods.getSize());
    }

    private static PageImpl<ReadFoodListResponseDto> getFoodListSorted(String latitude, String longitude, Pageable pageable, Page<Food> foods) {
        List<ReadFoodListResponseDto> result = foods.stream()
                .map(food -> {
                    double distanceDiff = distance(
                            Double.parseDouble(latitude),
                            Double.parseDouble(longitude),
                            Double.parseDouble(food.getLatitude()),
                            Double.parseDouble(food.getLongtitude()),
                            "M");
                    return ReadFoodListResponseDto.builder()
                            .food(food)
                            .distance(distanceDiff)
                            .build();
                })
                .collect(Collectors.toList());

        return new PageImpl<>(result, pageable, foods.getSize());
    }
    /**
     * 두 지점간의 거리 계산
     *
     * @param lat1 지점 1 위도
     * @param lon1 지점 1 경도
     * @param lat2 지점 2 위도
     * @param lon2 지점 2 경도
     * @param unit 거리 표출단위
     * @return
     */
    private static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;


        if (unit.equals("KM")) {
            dist = dist * 1.609344;
        } else if (unit.equals("M")) {
            dist = dist * 1609.344;
        }
        dist = Math.round(dist * 100.0) / 100.0;
        return dist;
    }


    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
