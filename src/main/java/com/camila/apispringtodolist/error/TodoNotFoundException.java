package com.camila.apispringtodolist.error;

public class TodoNotFoundException extends Throwable {
    public TodoNotFoundException(String message) {
        super(message);
    }
}
