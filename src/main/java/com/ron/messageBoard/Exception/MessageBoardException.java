package com.ron.messageBoard.Exception;

import lombok.Data;

@Data
public class MessageBoardException extends RuntimeException {

    private String message;

    public MessageBoardException() {
    }

    public MessageBoardException(String message) {
        this.message = message;
    }

    public MessageBoardException(String message, String message1) {
        super(message);
        this.message = message1;
    }

    public MessageBoardException(String message, Throwable cause, String message1) {
        super(message, cause);
        this.message = message1;
    }

    public MessageBoardException(Throwable cause, String message) {
        super(cause);
        this.message = message;
    }

    public MessageBoardException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String message1) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message1;
    }
}
