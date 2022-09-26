package com.hyun.blog.application;

import com.hyun.blog.domain.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.of;

@RequestMapping("/users")
@RestController
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponseDTO postUser(@RequestBody UserPostRequestDTO postRequestDTO){
        return UserResponseDTO.fromAuthorizedUser(
                userService.signUp(postRequestDTO.toUser()));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody UserLoginRequestDTO loginRequestDTO) {
        return of(userService.login(loginRequestDTO.getEmail(),loginRequestDTO.getPassword()).map(UserResponseDTO::fromAuthorizedUser));
    }
}
