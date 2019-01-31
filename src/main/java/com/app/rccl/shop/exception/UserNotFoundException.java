package com.app.rccl.shop.exception;

public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        super("User not found!");
    }

}
