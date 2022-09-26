package com.hyun.blog.domain;

import com.hyun.blog.domain.jwt.JWTService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JWTService jwtService;

    public UserService(UserRepository userRepository,JWTService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Transactional(readOnly = true)
    public Optional<AuthorizedUser> login(String email,String password) {
        return userRepository.findFirstByEmailAndPassword(email,password).map(this::authorizeUser);
    }

    public AuthorizedUser signUp(User user) {
        return authorizeUser(userRepository.save(user));
    }

    private AuthorizedUser authorizeUser(User user) {
        return AuthorizedUser.fromUser(user,jwtService.generateTokenFromUser(user));
    }


}
