package com.example.foodlog.global.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CommonResDto<T>{
    private int code; // 1(성공),-1(실패)
    private String message;
    private T data;
}