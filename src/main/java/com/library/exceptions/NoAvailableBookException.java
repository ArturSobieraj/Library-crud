package com.library.exceptions;

public class NoAvailableBookException extends Exception {
    public NoAvailableBookException(String message) {
        super(message);
    }
}
