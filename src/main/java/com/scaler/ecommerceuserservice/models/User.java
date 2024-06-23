package com.scaler.ecommerceuserservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class User extends BaseModel {
    private String firstName;
    private String lastName;
    private String email;
    private String hashedPassword;

    @ManyToMany
    private List<Role> roles;

    private Boolean isEmailVerified;

}