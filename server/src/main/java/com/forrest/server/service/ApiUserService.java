package com.forrest.server.service;

import com.forrest.server.util.SecurityUtil;
import com.forrest.server.util.exception.AlreadyExistEmailException;
import com.forrest.server.web.dto.request.SignUpReqDto;
import com.forrest.server.web.entity.authority.Authority;
import com.forrest.server.web.entity.user.User;
import com.forrest.server.web.entity.user.UserRepository;
import java.util.Collections;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Created by Bloo
 * @Date: 2021/07/11
 */


@Slf4j
@RequiredArgsConstructor
@Service
public class ApiUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUp ( SignUpReqDto signUpReqDto ) {
        isDuplicateEmail(signUpReqDto.getUsername());

        Authority authority = Authority.builder()
            .authorityName("ROLE_USER")
            .build();

        User user = User.builder()
            .username(signUpReqDto.getUsername())
            .password(passwordEncoder.encode(signUpReqDto.getPassword()))
            .nickName(signUpReqDto.getNickName())
            .authorities(Collections.singleton(authority))
            .activated(true)
            .build();

        userRepository.save(user);
    }

    private void isDuplicateEmail ( String email ) {
        userRepository.findOneWithAuthoritiesByUsername(email).ifPresent(
            user -> {
                throw new AlreadyExistEmailException();
            });
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities(String username) {
        return userRepository.findOneWithAuthoritiesByUsername(username);
    }

    @Transactional (readOnly = true)
    public Optional<User> getMyUserWithAuthorities() {
        return SecurityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername);
    }
}
