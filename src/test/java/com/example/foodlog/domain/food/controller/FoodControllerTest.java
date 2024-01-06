package com.example.foodlog.domain.food.controller;

import com.example.foodlog.domain.food.dto.response.ReadFoodListResponseDto;
import com.example.foodlog.domain.food.entity.Food;
import com.example.foodlog.util.ControllerTestSupport;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

}