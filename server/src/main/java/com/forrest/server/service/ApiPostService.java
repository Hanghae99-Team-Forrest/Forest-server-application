package com.forrest.server.service;

import com.forrest.server.web.entity.category.CategoryRepository;
import com.forrest.server.web.entity.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Created by Bloo
 * @Date: 2021/07/10
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class ApiPostService {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;


}
