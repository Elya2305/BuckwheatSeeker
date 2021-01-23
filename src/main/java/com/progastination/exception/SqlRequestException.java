package com.progastination.exception;

public class SqlRequestException extends RuntimeException {
    public SqlRequestException() {
    }

    public SqlRequestException(String message) {
        super(message);
    }
}
