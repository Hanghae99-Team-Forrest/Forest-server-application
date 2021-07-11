package com.forrest.server.service;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.forrest.server.enumclass.TestUser;
import com.forrest.server.util.enumclass.UserRole;
import com.forrest.server.util.exception.AlreadyExistEmailException;
import com.forrest.server.web.dto.request.SignUpReqDto;
import com.forrest.server.web.entity.user.User;
import com.forrest.server.web.entity.user.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Created by Bloo
 * @Date: 2021/07/11
 */


@ExtendWith(MockitoExtension.class)
class ApiUserServiceTests {

    @InjectMocks
    private ApiUserService userService;

    @Mock
    private UserRepository userRepository;

    @Spy
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private SignUpReqDto signUpReqDto;

    private User user;

    @BeforeEach
    void setUp () {
        signUpReqDto = SignUpReqDto.builder()
            .email(TestUser.USER_EMAIL)
            .password(TestUser.USER_PWD)
            .nickName(TestUser.USER_NICK_NAME)
            .build();

        user = User.builder()
            .email(TestUser.USER_EMAIL)
            .password(TestUser.USER_PWD)
            .nickName(TestUser.USER_NICK_NAME)
            .userRole(UserRole.ROLE_USER)
            .build();

    }

    @DisplayName ("회원가입 테스트")
    @Test
    void signUp () {
        // given
        // when
        userService.signUp(signUpReqDto);
        // then
        verify(userRepository, times(1)).save(any(User.class));
    }

    @DisplayName ("중복된 이메일 테스트")
    @Test
    void ifAlreadyExistEmail () {
        // given
        // when
        when(userRepository.findByEmail(TestUser.USER_EMAIL))
            .thenReturn(Optional.of(user));

        // then
        assertThrows(
            AlreadyExistEmailException.class, () -> userService.signUp(signUpReqDto)
        );
    }
}