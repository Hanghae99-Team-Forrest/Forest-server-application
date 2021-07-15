package com.forrest.server.web.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forrest.server.enumclass.TestUser;
import com.forrest.server.service.ApiUserService;
import com.forrest.server.web.dto.request.SignUpReqDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * @Created by Bloo
 * @Date: 2021/07/11
 */

@WebMvcTest(ApiUserController.class)
class ApiUserControllerTest {

    private static final String URL = "/api/users";

    @MockBean
    private ApiUserService apiUserService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private SignUpReqDto signUpReqDto;


    @BeforeEach
    void setUp ( WebApplicationContext webApplicationContext ) {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .addFilter(new CharacterEncodingFilter("UTF-8"))
            .alwaysDo(print())
            .build();

        signUpReqDto = SignUpReqDto.builder()
            .email(TestUser.USER_EMAIL)
            .password(TestUser.USER_PWD)
            .nickName(TestUser.USER_NICK_NAME)
            .build();
    }

    @DisplayName ("회원가입 성공을 테스트")
    @Test
    void  signUp() throws Exception {
        // given
        // when
        mockMvc.perform(post(URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(signUpReqDto))
        )
            .andExpect(status().isCreated());
        // then

    }
}