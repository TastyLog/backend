package com.example.foodlog.domain.food.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.ZSetOperations;

@NoArgsConstructor
@Getter
public class SearchRankListResponseDto {
    private String keyword;
    private Double score;

    public SearchRankListResponseDto(ZSetOperations.TypedTuple<String> rankingData) {
        this.keyword = rankingData.getValue();
        this.score = rankingData.getScore();
    }
}
