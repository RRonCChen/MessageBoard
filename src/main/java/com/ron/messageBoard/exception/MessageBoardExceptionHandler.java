package com.ron.messageBoard.exception;

import com.ron.messageBoard.entity.api.response.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class MessageBoardExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = MessageBoardNotFountException.class)
    protected ResponseEntity<ApiResponse> handleNotFound(MessageBoardNotFountException ex) {
        return new ResponseEntity<>(new ApiResponse().notFound(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {MessageBoardParameterErrorException.class, ConstraintViolationException.class})
    protected ResponseEntity<ApiResponse> handleParameterError(Exception ex) {
        return new ResponseEntity<>(new ApiResponse().badRequest(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MessageBoardException.class)
    protected ResponseEntity<ApiResponse> handleServerError(MessageBoardException ex) {
        return new ResponseEntity<>(new ApiResponse().serverError(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus("Error");
        apiResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(apiResponse, headers, status);
    }

}
