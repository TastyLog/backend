package com.example.foodlog.global.exception;

import com.example.foodlog.global.exception.dto.CommonResponse;
import com.example.foodlog.global.exception.dto.ErrorResponse;
import com.example.foodlog.global.exception.error.YoutuberNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.net.BindException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 유튜버를 찾지 못했을 때
     */
    @ExceptionHandler(YoutuberNotFoundException.class)
    protected ResponseEntity<?> handleBoardNotFoundException(YoutuberNotFoundException ex) {
        ErrorCode errorCode = ErrorCode.YOUTUBER_NOT_FOUND_EXCEPTION;

        ErrorResponse error = ErrorResponse.builder()
                .status(errorCode.getStatus().value())
                .message(errorCode.getMessage())
                .code(errorCode.getCode())
                .build();

        CommonResponse response = CommonResponse.builder()
                .success(false)
                .error(error)
                .build();

        return new ResponseEntity<>(response, errorCode.getStatus());
    }
    /**
     * 리퀘스트 파라미터 바인딩이 실패했을때
     */
    @ExceptionHandler(BindException.class)
    protected ResponseEntity<CommonResponse> handleRequestParameterBindException(BindException ex) {

        ErrorCode errorCode = ErrorCode.REQUEST_PARAMETER_BIND_EXCEPTION;

        ErrorResponse error = ErrorResponse.builder()
                .status(errorCode.getStatus().value())
                .message(errorCode.getMessage())
                .code(errorCode.getCode())
                .build();

        CommonResponse response = CommonResponse.builder()
                .success(false)
                .error(error)
                .build();

        return new ResponseEntity<>(response, errorCode.getStatus());
    }

    /**
     *
     * 유효성검사에 실패하는
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> argumentNotValidException(BindingResult bindingResult,MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        ErrorCode errorCode = ErrorCode.REQUEST_PARAMETER_BIND_EXCEPTION;

        List<String> errorMessages = fieldErrors.stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        ErrorResponse error = ErrorResponse.builder()
                .status(errorCode.getStatus().value())
                .message(errorMessages.toString())
                .code(errorCode.getCode())
                .build();

        CommonResponse response = CommonResponse.builder()
                .success(false)
                .error(error)
                .build();

        return new ResponseEntity<>(response, errorCode.getStatus());
    }

    /**
     *  주로 파일 업로드나 멀티파트 요청에서 파트나 매개변수가 누락된 경우에 해당 예외
     */

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<?> missingServletRequestPartException(MissingServletRequestPartException exception) {
//        Sentry.captureException(exception);
        log.error("MissingServletRequestPartException = {}", exception);
        return ResponseEntity.badRequest().body("MissingServletRequestPartException");
    }
    /**
     *
     * 유효성검사 타입 불일치
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {

        log.error("MethodArgumentTypeMismatchException = {}", exception);
        return ResponseEntity.badRequest().body("잘못된 형식의 값입니다.");
    }
}