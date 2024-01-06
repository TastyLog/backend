package com.example.foodlog.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    REQUEST_PARAMETER_BIND_EXCEPTION(HttpStatus.BAD_REQUEST, "REQ_001", "PARAMETER_BIND_FAILED"),
    YOUTUBER_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "REQ_002","등록된 유튜버가 없습니다"),
    FOOD_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "REQ_003","등록된 맛집이 없습니다");


    private final String code;
    private final String message;
    private final HttpStatus status;

    ErrorCode(final HttpStatus status, final String code, final String message){
        this.status = status;
        this.message = message;
        this.code = code;
    }
}