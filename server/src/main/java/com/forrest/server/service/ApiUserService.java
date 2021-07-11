package com.forrest.server.service;

import com.forrest.server.util.exception.AlreadyExistEmailException;
import com.forrest.server.web.dto.request.SignUpReqDto;
import com.forrest.server.web.entity.user.User;
import com.forrest.server.web.entity.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        isDuplicateEmail(signUpReqDto.getEmail());
        User user = signUpReqDto.toEntity(passwordEncoder.encode(signUpReqDto.getEmail()));
        userRepository.save(user);
    }

    private void isDuplicateEmail ( String email ) {
        userRepository.findByEmail(email).ifPresent(
            user -> {
                throw new AlreadyExistEmailException();
            });
    }
}
