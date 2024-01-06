package com.example.foodlog.global.exception.error;

public class FoodNotFoundException extends RuntimeException {
    public FoodNotFoundException() {
        super();
    }
    public FoodNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public FoodNotFoundException(String message) {
        super(message);
    }
    public FoodNotFoundException(Throwable cause) {
        super(cause);
    }

}