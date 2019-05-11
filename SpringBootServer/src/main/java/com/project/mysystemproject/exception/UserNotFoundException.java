package com.project.mysystemproject.exception;

public class UserNotFoundException extends Exception {

private String username;

public UserNotFoundException(String username) {
        super(String.format("User is not found with username : '%s'", username));
        }

}