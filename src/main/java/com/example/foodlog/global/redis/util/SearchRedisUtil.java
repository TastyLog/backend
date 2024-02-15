package com.example.foodlog.global.redis.util;


import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor
@Service
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
        }
    }
}