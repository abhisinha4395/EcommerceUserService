package com.scaler.ecommerceuserservice.exception_handlers;

import com.scaler.ecommerceuserservice.dtos.ExceptionDto;
import com.scaler.ecommerceuserservice.exceptions.EmailAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ExceptionDto> handleUserAlreadyExistsException(EmailAlreadyExistsException ex) {
        ExceptionDto dto = new ExceptionDto();
        dto.setEmail(ex.getEmail());
        dto.setMessage(ex.getMessage());

        return new ResponseEntity<>(dto, HttpStatus.CONFLICT);
    }
}
