package com.scaler.ecommerceuserservice.dtos;

import com.scaler.ecommerceuserservice.models.Role;
import com.scaler.ecommerceuserservice.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private List<Role> roles;

    public static UserDto from(User user){
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setRoles(user.getRoles());

        return userDto;
    }
}
