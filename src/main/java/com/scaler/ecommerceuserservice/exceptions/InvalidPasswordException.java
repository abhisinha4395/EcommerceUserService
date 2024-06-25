package com.scaler.ecommerceuserservice.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidPasswordException extends Exception {

    //private String message;
    private String email;

    public InvalidPasswordException(String email, String message){
        super(message);
        this.email = email;
    }
}
