package com.example.foodlog.global.exception.error;

public class YoutuberNotFoundException extends RuntimeException {
    public YoutuberNotFoundException() {
        super();
    }
    public YoutuberNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public YoutuberNotFoundException(String message) {
        super(message);
    }
    public YoutuberNotFoundException(Throwable cause) {
        super(cause);
    }

}