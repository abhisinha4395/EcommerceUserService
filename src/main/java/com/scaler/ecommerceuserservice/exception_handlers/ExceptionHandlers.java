package com.scaler.ecommerceuserservice.exception_handlers;

import com.scaler.ecommerceuserservice.dtos.ExceptionDto;
import com.scaler.ecommerceuserservice.exceptions.InvalidPasswordException;
import com.scaler.ecommerceuserservice.exceptions.InvalidTokenException;
import com.scaler.ecommerceuserservice.exceptions.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ExceptionDto> handleInvalidPasswordException(InvalidPasswordException ex) {
        ExceptionDto dto = new ExceptionDto();
        dto.setMessage(ex.getMessage());
        dto.setEmail(ex.getEmail());

        return new ResponseEntity<>(dto, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleInvalidUserException(UsernameNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ExceptionDto> handleUserExistsException(UserAlreadyExistsException ex){
        ExceptionDto dto = new ExceptionDto();
        dto.setMessage(ex.getMessage());
        dto.setEmail(ex.getEmail());
        return new ResponseEntity<>(dto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<String> handleInvalidTokenException(InvalidTokenException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
