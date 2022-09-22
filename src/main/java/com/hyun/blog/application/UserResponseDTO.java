package com.hyun.blog.application;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.hyun.blog.domain.User;
import lombok.Getter;

@JsonTypeName("user")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@Getter
public class UserResponseDTO {

    private final String email;
    private final String token;
    private final String username;
    private final String bio;
    private final String image;

    public UserResponseDTO(User user) {
        this.email = user.getEmail();
        this.token = "";
        this.username = user.getUsername();
        this.bio = user.getBio();
        this.image = user.getImage();
    }


    public static UserResponseDTO fromUser(User user){
        return new UserResponseDTO(user);
    }

}
