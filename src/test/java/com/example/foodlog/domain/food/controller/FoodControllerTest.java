package com.example.foodlog.domain.food.controller;

import com.example.foodlog.util.ControllerTestSupport;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class FoodControllerTest extends ControllerTestSupport {


    @DisplayName("음식점 리스트 사이즈를 10으로 설정하면 음식점리스트는 10개가 출력된다.")
    @Test
    void givenPageSize10ShouldReturnRestaurantListWithSize10() throws Exception {
        //given
        int count=0;
        int size=10;
        String url = "/api/v1/food/37.5070308/127.0586576?page=0&size="+size;
        //when
        MvcResult mvcResult = mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //then
        mvcResult.getResponse().setContentType("application/json;charset=UTF-8");
        String result = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = objectMapper.readTree(result);
        JsonNode resultData = jsonNode.get("data");
        JsonNode resultContent = resultData.get("content");
        for (JsonNode resultContentItem : resultContent) {
            JsonNode uniqueKeyNode = resultContentItem.get("uniqueKey");
            if (uniqueKeyNode != null && !uniqueKeyNode.asText().isEmpty()) {
                count++;
            }
        }
        Assertions.assertThat(count).isEqualTo(size);
    }

    @DisplayName("searchWord에 해당하는 키워드에 해당하는 음식점을 보여준다.")
    @Test
    void matchedRestaurants() throws Exception {
        //given
        String searchWord = "돈까스";
        String url = "/api/v1/food/37.5070308/127.0586576?searchWord=" + searchWord + "&page=0&size=100000";
        int expectedCount = 0; // 예상되는 음식점 수

        //when
        MvcResult mvcResult = mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //then
        mvcResult.getResponse().setContentType("application/json;charset=UTF-8");
        String result = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = objectMapper.readTree(result);
        JsonNode resultData = jsonNode.get("data");
        JsonNode resultContent = resultData.get("content");

        for (JsonNode resultContentItem : resultContent) {

            JsonNode restaurantName = resultContentItem.get("restaurantName");
            JsonNode category = resultContentItem.get("category");
            JsonNode address = resultContentItem.get("address");

            if (restaurantName != null && containsSearchWord(restaurantName.asText(),searchWord)) {
                expectedCount++;
            }
            else if (category != null && containsSearchWord(category.asText(),searchWord)) {
                expectedCount++;
            }
            else if (address != null && containsSearchWord(address.asText(),searchWord)) {
                expectedCount++;
            }
        }

        // 응답으로 받은 음식점 수와 예상되는 음식점 수를 비교
        int actualCount = resultContent.size();
        Assertions.assertThat(actualCount).isEqualTo(expectedCount);
    }

    // ngram 함수를 통해 문자열을 2글자씩 파싱하여 배열에 저장하는 함수
    private String[] ngram(String str) {
        List<String> ngrams = new ArrayList<>();
        for (int i = 0; i < str.length() - 1; i++) {
            ngrams.add(str.substring(i, i + 2));
        }
        return ngrams.toArray(new String[0]);
    }

    // 주어진 문자열이 검색어를 포함하는지 확인하고, 2글자씩 파싱하여 검색어와 일치하는지 검사하는 함수
    private boolean containsSearchWord(String restaurantName, String searchWord) {
        if (restaurantName != null && searchWord != null) {
            String[] restaurantNgrams = ngram(restaurantName);
            String[] searchWordNgrams = ngram(searchWord);
            for (String ngram : restaurantNgrams) {
                if (Arrays.asList(searchWordNgrams).contains(ngram)) {
                    return true;
                }
            }
        }
        return false;
    }

}