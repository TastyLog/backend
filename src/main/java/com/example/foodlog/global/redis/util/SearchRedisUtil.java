package com.example.foodlog.global.redis.util;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor
@Service
@Slf4j
public class SearchRedisUtil {
    private final StringRedisTemplate stringRedisTemplate;

    /**
     * ranking keword 추출
     * @param key
     * @return
     */
    public Set<ZSetOperations.TypedTuple<String>> getTop10PopularKeywords(String key) {
        ZSetOperations<String, String> ZSetOperations = stringRedisTemplate.opsForZSet();
        Set<ZSetOperations.TypedTuple<String>> typedTuples = ZSetOperations.reverseRangeWithScores(key, 0, 9);  //score순으로 10개 보여줌
        return typedTuples;
    }
    /**
     *
     * @param keyword 검색키워드
     * 해당 검색 키워드 입력할 때마다 incrementScore +1
     */
    public void incrementSearchKeywordScore(String keyword) {
        String stripKeyword =keyword.strip();
        Double score = stringRedisTemplate.opsForZSet().score("ranking", stripKeyword);
        if (score != null) {
            stringRedisTemplate.opsForZSet().incrementScore("ranking", stripKeyword, 1);
        } else {
            stringRedisTemplate.opsForZSet().add("ranking", stripKeyword, 1);
            stringRedisTemplate.opsForHash().put("state", stripKeyword, "new");
        }
    }
    /**
     * redis ranking 키 값을 가진 모든 요소 초기화
     */
    public void resetSearchKeywordScores() {
        stringRedisTemplate.opsForZSet().removeRange("ranking", 0, -1);
    }
    public String getState(String keyword){
        return (String) stringRedisTemplate.opsForHash().get("state", keyword);
    }
    public void updateState(String state,String keyword){
        stringRedisTemplate.opsForHash().put("state", keyword, state);
    }

    public void copyRankingDataToPrevious() {
        log.info("copyRankingDataToPrevious");
        // 이전 랭킹 데이터를 모두 삭제합니다.
        stringRedisTemplate.delete("previousRanking");
        // ranking 키의 데이터를 가져와서 previousRanking 키로 복사합니다.
        Set<ZSetOperations.TypedTuple<String>> rankingData = stringRedisTemplate.opsForZSet().rangeWithScores("ranking", 0, -1);
        if (rankingData != null && !rankingData.isEmpty()) {
            for (ZSetOperations.TypedTuple<String> tuple : rankingData) {
                String keyword = tuple.getValue();
                Double score = tuple.getScore();
                stringRedisTemplate.opsForZSet().add("previousRanking", keyword, score);
            }
        }
    }

}