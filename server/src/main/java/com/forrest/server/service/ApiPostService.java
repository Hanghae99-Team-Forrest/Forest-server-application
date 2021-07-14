package com.forrest.server.service;

import com.forrest.server.util.exception.CategoryNotFoundException;
import com.forrest.server.util.s3.S3FileUploader;
import com.forrest.server.web.dto.request.PostSaveDto;
import com.forrest.server.web.entity.category.Category;
import com.forrest.server.web.entity.category.CategoryRepository;
import com.forrest.server.web.entity.user.UserRepository;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Created by Bloo
 * @Date: 2021/07/10
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class ApiPostService {

    private static final String DIR_NAME = "static";

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final S3FileUploader s3FileUploader;

    public void savePost ( PostSaveDto saveDto ) throws IOException {
        Long categoryId = saveDto.getCategoryId();

        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(CategoryNotFoundException::new);

        String url = s3FileUploader.upload(saveDto.getMultipartFile(), DIR_NAME);
        saveDto.toEntity(url, category);
    }





    // --------------------- 테스트 ----------------------------

    public String savePost2 ( PostSaveDto saveDto, MultipartFile multipartFile ) throws IOException {
//        MultipartFile multipartFile = saveDto.getMultipartFile();
        System.out.println("파일 두개 호출");
        System.out.println(saveDto.getContent());

        String url = s3FileUploader.upload(multipartFile, DIR_NAME);

        return url;
    }
}
