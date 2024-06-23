package com.scaler.ecommerceuserservice.services;

import com.scaler.ecommerceuserservice.dtos.SignUpRequestDto;
import com.scaler.ecommerceuserservice.exceptions.EmailAlreadyExistsException;
import com.scaler.ecommerceuserservice.models.Token;
import com.scaler.ecommerceuserservice.models.User;
import com.scaler.ecommerceuserservice.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User signUp(String firstName, String lastName, String email,
                       String password) throws EmailAlreadyExistsException {
        User newUser = new User();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        newUser.setHashedPassword(bCryptPasswordEncoder.encode(password));
        newUser.setIsEmailVerified(true);
        return userRepository.save(newUser);

    }

    public Token login(String email, String password){
        return null;
    }

    public void logout(String token){
    }

    public User validateToken(String token){
        return null;
    }
}
