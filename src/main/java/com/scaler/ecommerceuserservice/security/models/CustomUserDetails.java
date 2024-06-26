package com.scaler.ecommerceuserservice.security.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.scaler.ecommerceuserservice.models.Role;
import com.scaler.ecommerceuserservice.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//This is the adapter class b/w User and Spring Security
@JsonDeserialize
@NoArgsConstructor
@Setter
@Getter
public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private boolean accountNonExpired;

    private boolean accountNonLocked;
    private boolean credentialsNonExpired;

    private boolean enabled;

    private List<CustomGrantedAuthority> authorities;

    private Long userId;

    public CustomUserDetails(User user){
        this.username = user.getEmail();
        this.password = user.getHashedPassword();
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
        this.userId = user.getId();


        this.authorities = new ArrayList<>();
        for(Role role : user.getRoles()){
            authorities.add(new CustomGrantedAuthority(role));
        }
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

//    public Long getUserId() { return userId; }
}
