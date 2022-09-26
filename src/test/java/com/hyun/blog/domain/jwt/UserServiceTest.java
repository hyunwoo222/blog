package com.hyun.blog.domain.jwt;

import com.hyun.blog.domain.AuthorizedUser;
import com.hyun.blog.domain.User;
import com.hyun.blog.domain.UserRepository;
import com.hyun.blog.domain.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private JWTService jwtService;

    @BeforeEach
    void initializeService() {
        this.userService = new UserService(userRepository,jwtService);
    }

    @Test
    void when_signUp_expect_userRepository_save_called(@Mock User user) {

        given(userRepository.save(user)).willReturn(user);

        userService.signUp(user);

        then(userRepository).should(times(1)).save(user);
    }

    @Test
    void when_signUp_expect_jwtService_to_generateToken(@Mock User user){
        String mockedToken = "MOCKED_TOKEN";
        when(userRepository.save(user)).thenReturn(user);
        when(jwtService.generateTokenFromUser(user)).thenReturn(mockedToken);

        assertThat(userService.signUp(user)).extracting(AuthorizedUser::getToken).isEqualTo(mockedToken);
    }

    @Test
    void when_login_expet_userRepository_findFirstByEmailANdPassword_called() {
        String email = "email";
        String password = "password";

        userService.login(email,password);

        then(userRepository).should(times(1)).findFirstByEmailAndPassword(email,password);
    }

    @Test
    void when_login_expect_jwtService_to_generateToken(@Mock User user) {
        given(userRepository.findFirstByEmailAndPassword(anyString(),anyString())).willReturn(of(user));
        userService.login("email","password");
        then(jwtService).should(times(1)).generateTokenFromUser(user);

    }

}
