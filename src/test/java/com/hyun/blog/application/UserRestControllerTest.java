package com.hyun.blog.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyun.blog.domain.AuthorizedUser;
import com.hyun.blog.domain.User;
import com.hyun.blog.domain.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

import static java.util.Optional.of;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WebMvcTest(UserRestController.class)
class UserRestControllerTest {

    @MockBean
    private UserService userService;

    @Resource
    private MockMvc mockMvc;

    @Resource
    private ObjectMapper objectMapper;


    @Test
    void 测试登陆用户_when_post_login_expect_userService_findFirstByEmailAndPassword_called(@Mock AuthorizedUser authorizedUser) throws Exception {
        UserLoginRequestDTO loginDTO = new UserLoginRequestDTO("email","passrword");
        when(userService.login(loginDTO.getEmail(), loginDTO.getPassword()))
                .thenReturn(of(authorizedUser));

        mockMvc.perform(post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(loginDTO))
        );

        then(userService).should(times(1))
        .login(loginDTO.getEmail(), loginDTO.getPassword());

    }


    @Test
    void 测试登陆用户_when_post_login_expect_valid_response_body() throws Exception {
        AuthorizedUser authorizedUser = mockAuthorizedUser();
        when(userService.login(anyString(),anyString()))
                .thenReturn(of(authorizedUser));


        mockMvc.perform(post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes((new UserLoginRequestDTO("email","password")))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user").exists())
                .andExpect(jsonPath("$.user.email").hasJsonPath())
                .andExpect(jsonPath("$.user.bio").hasJsonPath())
                .andExpect(jsonPath("$.user.image").hasJsonPath())
                .andExpect(jsonPath("$.user.token").hasJsonPath());

    }

    private AuthorizedUser mockAuthorizedUser() {
        AuthorizedUser mockedUser = mock(AuthorizedUser.class);
        when(mockedUser.getUsername()).thenReturn("");
        when(mockedUser.getEmail()).thenReturn("");
        when(mockedUser.getBio()).thenReturn("");
        when(mockedUser.getImage()).thenReturn("");
        when(mockedUser.getToken()).thenReturn("");
        return mockedUser;
    }


    @Test
    void 测试注册用户_when_post_users_expect_userService_createUser_called(@Mock AuthorizedUser authorizedUser) throws Exception {
        UserPostRequestDTO postRequestDTO = new UserPostRequestDTO("username","email","password");

        given(userService.signUp(any(User.class))).willReturn(authorizedUser);

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(postRequestDTO)));

        then(userService).should(times(1)).signUp(any(User.class));

    }


    /**
     * @Mock AuthorizedUser authorizedUser
     * @param authorizedUser
     * @throws Exception
     */
    @Test
    void 测试注册用户_when_post_users_expect_valid_response(@Mock AuthorizedUser authorizedUser) throws Exception {

        when(userService.signUp(any(User.class))).thenReturn(authorizedUser);

        mockMvc.perform(post("/users")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new UserLoginRequestDTO("email", "password"))))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user").hasJsonPath())
                .andExpect(jsonPath("$.user.email").hasJsonPath())
                .andExpect(jsonPath("$.user.bio").hasJsonPath())
                .andExpect(jsonPath("$.user.image").hasJsonPath())
                .andExpect(jsonPath("$.user.token").hasJsonPath());
    }

}