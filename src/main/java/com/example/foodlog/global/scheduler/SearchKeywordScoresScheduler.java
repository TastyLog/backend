package com.example.foodlog.global.scheduler;

import com.example.foodlog.global.redis.util.SearchRedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

@Component
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
public class SearchKeywordScoresScheduler {

    private final SearchRedisUtil searchRedisUtil;
    private static final String rankingUp="up";
    private static final String rankingDown="down";
    private static final String rankingNone="none";

    /**
     * *           *　　　　　　*　　　　　　*　　　　　　*　　　　　　*
     * 초(0-59)   분(0-59)　　시간(0-23)　　일(1-31)　　월(1-12)　　요일(0-7)
     * 각 별 위치에 따라 주기를 다르게 설정 할 수 있다.
     * 순서대로 초-분-시간-일-월-요일 순이다. 그리고 괄호 안의 숫자 범위 내로 별 대신 입력 할 수도 있다.
     * 요일에서 0과 7은 일요일이며, 1부터 월요일이고 6이 토요일이다.
     */
    @Scheduled(cron = "0 0 0 * * MON", zone = "Asia/Seoul") // 매주 월요일 자정에 실행
    public void resetScoresDaily() {
        log.info("working ResetSearchKeywordScoresScheduler");
        searchRedisUtil.resetSearchKeywordScores(); // 메서드 호출
    }

    /**
     * 1시간 마다 스케줄러에 의해 상태값이 업데이트 되는 로직
     */
    @Scheduled(cron = "0 0 */1 * * ?", zone = "Asia/Seoul")
    public void updateSearchKeywordState(){
        log.info("updateSearchKeywordState");
        Set<ZSetOperations.TypedTuple<String>> previousRanking = searchRedisUtil.getTop10PopularKeywords("previousRanking");

        // 이전 데이터가 있으면 ranking데이터와 previousRanking을 비교해서 0보다 크면 up, 0보다 작으면 down, 0이면 none으로
        // state 값을 초기화해줘야한다.
        AtomicLong previousRank = new AtomicLong(1);
        if(!previousRanking.isEmpty()){
            Set<ZSetOperations.TypedTuple<String>> currentRanking = searchRedisUtil.getTop10PopularKeywords("ranking");
            // 이전 랭킹과 현재 랭킹을 비교하여 state 값을 설정한다.
            for (ZSetOperations.TypedTuple<String> previousTuple : previousRanking) {
                String previousKeyword = previousTuple.getValue();
                long  previousRankAndIncrement= previousRank.getAndIncrement();
                AtomicLong currentRank = new AtomicLong(1);
                for (ZSetOperations.TypedTuple<String> currentTuple : currentRanking) {
                    String currentKeyword = currentTuple.getValue();
                    long  currentRankAndIncrement= currentRank.getAndIncrement();
                    if(previousKeyword.equals(currentKeyword)){
                        if(currentRankAndIncrement<previousRankAndIncrement){
                            searchRedisUtil.updateState(rankingUp,currentKeyword);
                        }else if(currentRankAndIncrement> previousRankAndIncrement){
                            searchRedisUtil.updateState(rankingDown,currentKeyword);
                        }else{
                            searchRedisUtil.updateState(rankingNone,currentKeyword);
                        }
                    }
                }
            }
        }
        // 마지막에 ranking 데이터를 previous라는 키값으로 덮어쓰기해서 ranking 데이터가 previous로 가도록 로직을 수행한다.
        searchRedisUtil.copyRankingDataToPrevious();
    }
}
