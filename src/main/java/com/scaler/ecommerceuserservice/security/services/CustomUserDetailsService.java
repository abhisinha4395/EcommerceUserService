package com.scaler.ecommerceuserservice.security.services;

import com.scaler.ecommerceuserservice.models.User;
import com.scaler.ecommerceuserservice.repositories.UserRepository;
import com.scaler.ecommerceuserservice.security.models.CustomUserDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(username);
        if (optionalUser.isEmpty()){
            throw new UsernameNotFoundException("User with email:" + username + "not found");
        }

        return new CustomUserDetails(optionalUser.get());
    }
}
