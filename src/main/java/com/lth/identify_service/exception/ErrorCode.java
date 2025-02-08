package com.lth.identify_service.exception;

public enum ErrorCode {
    USER_EXISTS(1001, "User already exists"),
    USER_NAME_INVALID(1002, "username has to be between 5 and 20 characters"),
    PASSWORD_INVALID(1003, "password has to be between 8 and 20 characters"),
    INVALID_MESSAGE_KEY(1004, "Invalid message key"),
    ;
    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
