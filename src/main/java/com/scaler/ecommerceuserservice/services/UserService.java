package com.scaler.ecommerceuserservice.services;

import com.scaler.ecommerceuserservice.exceptions.InvalidPasswordException;
import com.scaler.ecommerceuserservice.exceptions.InvalidTokenException;
import com.scaler.ecommerceuserservice.exceptions.UserAlreadyExistsException;
import com.scaler.ecommerceuserservice.models.Token;
import com.scaler.ecommerceuserservice.models.User;
import com.scaler.ecommerceuserservice.repositories.TokenRepository;
import com.scaler.ecommerceuserservice.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private TokenRepository tokenRepository;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
                       TokenRepository tokenRepository){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository = tokenRepository;
    }

    public User signUp(String firstName, String lastName, String email,
                       String password) throws UserAlreadyExistsException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()){
            throw new UserAlreadyExistsException(email, "User already exists");
        }

        User newUser = new User();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        newUser.setHashedPassword(bCryptPasswordEncoder.encode(password));
        newUser.setIsEmailVerified(true);
        newUser.setDeleted(false);
        return userRepository.save(newUser);

    }

    public Token login(String email, String password) throws UsernameNotFoundException, InvalidPasswordException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        User user = optionalUser.get();
        if (!bCryptPasswordEncoder.matches(password, user.getHashedPassword())){
            throw new InvalidPasswordException(email, "Either username/password doesn't match");
        }


        Optional<Token> optionalToken = tokenRepository.findByUserAndIsDeletedAndExpiryAtGreaterThan(
                user, false, LocalDate.now());

        if (optionalToken.isPresent()){
            return optionalToken.get();
        }

        Token token = generateToken(user);
        return tokenRepository.save(token);
    }

    private Token generateToken(User user){
        LocalDate currentDate = LocalDate.now();
        LocalDate expiryAt = currentDate.plusDays(30);

        Token token = new Token();
        token.setExpiryAt(expiryAt);
        token.setValue(RandomStringUtils.randomAlphanumeric(128));
        token.setUser(user);
        return token;
    }

    public void logout(String token){
        Optional<Token> optionalToken = tokenRepository.findByValueAndIsDeleted(
                token, false);
        if (optionalToken.isEmpty()){
            //throw exception
            return;
        }
        Token token1 = optionalToken.get();
        token1.setDeleted(true);
        tokenRepository.save(token1);
    }

    public User validateToken(String token) throws InvalidTokenException{
        Optional<Token> optionalToken = tokenRepository.findByValueAndIsDeletedAndExpiryAtGreaterThan(
                token, false, LocalDate.now());

        if (optionalToken.isEmpty()){
            throw new InvalidTokenException("Token has expired or invalid");
        }
        return optionalToken.get().getUser();
    }
}
