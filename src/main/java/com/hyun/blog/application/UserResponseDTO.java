package com.hyun.blog.application;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.hyun.blog.domain.AuthorizedUser;
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

    public static UserResponseDTO fromAuthorizedUser(AuthorizedUser user) {
        return new UserResponseDTO(user);
    }
    public UserResponseDTO(AuthorizedUser user) {
        this.email = user.getEmail();
        this.token = user.getToken();
        this.username = user.getUsername();
        this.bio = user.getBio();
        this.image = user.getImage();
    }


}
