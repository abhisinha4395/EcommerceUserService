package com.scaler.ecommerceuserservice.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAlreadyExistsException extends Exception{
    private String email;

    public UserAlreadyExistsException(String email, String message){
        super(message);
        this.email = email;
    }

}
