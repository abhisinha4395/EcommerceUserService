package com.scaler.ecommerceuserservice.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailAlreadyExistsException extends Exception {

    private String email;
    private String message;

    public EmailAlreadyExistsException(String email, String message){
        super(message);
        this.email = email;
    }
}
