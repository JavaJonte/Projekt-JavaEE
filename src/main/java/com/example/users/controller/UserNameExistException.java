package com.example.users.controller;

public class UserNameExistException extends Throwable {
    public UserNameExistException(String message) {
        super(message);
    }
}
