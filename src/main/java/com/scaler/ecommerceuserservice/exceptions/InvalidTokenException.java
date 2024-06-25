package com.scaler.ecommerceuserservice.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidTokenException extends Exception{

    public InvalidTokenException(String message){
        super(message);
    }
}
