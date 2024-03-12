package com.example.foodlog.domain.food.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SearchRankListResponseDto {
    private String keyword;
    private Double score;
    private Long rank;
    private String state;

    public SearchRankListResponseDto(long rank,String keyword,Double score,String state) {
        this.keyword = keyword;
        this.score = score;
        this.rank=rank;
        this.state=state;
    }
}
