package com.scaler.ecommerceuserservice.controllers;

import com.scaler.ecommerceuserservice.dtos.LogOutRequestDto;
import com.scaler.ecommerceuserservice.dtos.LoginRequestDto;
import com.scaler.ecommerceuserservice.dtos.SignUpRequestDto;
import com.scaler.ecommerceuserservice.dtos.UserDto;
import com.scaler.ecommerceuserservice.exceptions.InvalidPasswordException;
import com.scaler.ecommerceuserservice.exceptions.InvalidTokenException;
import com.scaler.ecommerceuserservice.exceptions.UserAlreadyExistsException;
import com.scaler.ecommerceuserservice.models.Token;
import com.scaler.ecommerceuserservice.models.User;
import com.scaler.ecommerceuserservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpRequestDto signupRequestDto)
            throws UserAlreadyExistsException {
        User user = userService.signUp(signupRequestDto.getFirstName(), signupRequestDto.getLastName(),
                signupRequestDto.getEmail(), signupRequestDto.getPassword());
        if (user == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(UserDto.from(user), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody LoginRequestDto loginRequestDto) throws InvalidPasswordException {
        Token token = userService.login(
                loginRequestDto.getEmail(), loginRequestDto.getPassword());
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogOutRequestDto logoutRequestDto){
        userService.logout(logoutRequestDto.getToken());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/validate/{token}")
    public ResponseEntity<UserDto> validateToken(@PathVariable("token") String token) throws InvalidTokenException {
        User user = userService.validateToken(token);

        return new ResponseEntity<>(UserDto.from(user), HttpStatus.OK);
    }
}
