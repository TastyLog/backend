package com.example.foodlog.domain.food.dto.response;


import com.example.foodlog.domain.food.entity.Youtuber;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ReadYoutuberListResponseDto {
    private String youtuberName;
    private String youtuberProfile;
    private Long youtuberId;
    private String youtuberChannelId;


    public ReadYoutuberListResponseDto(Youtuber youbuer) {
        this.youtuberName = youbuer.getYoutuberName();
        this.youtuberProfile = youbuer.getYoutuberProfile();
        this.youtuberId = youbuer.getId();
        this.youtuberChannelId = youbuer.getChannelId();
    }
}
