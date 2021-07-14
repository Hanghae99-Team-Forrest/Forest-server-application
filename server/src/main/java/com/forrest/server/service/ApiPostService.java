package com.forrest.server.service;

import com.forrest.server.util.exception.CategoryNotFoundException;
import com.forrest.server.util.exception.handler.PostNotFoundException;
import com.forrest.server.util.s3.S3FileUploader;
import com.forrest.server.web.dto.request.PostSaveDto;
import com.forrest.server.web.dto.response.PostDetailResponse;
import com.forrest.server.web.dto.response.PostSimpleResponse;
import com.forrest.server.web.entity.category.Category;
import com.forrest.server.web.entity.category.CategoryRepository;
import com.forrest.server.web.entity.post.PostRepository;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final S3FileUploader s3FileUploader;

    public void savePost ( PostSaveDto saveDto ) throws IOException {
        Long categoryId = saveDto.getCategoryId();

        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(CategoryNotFoundException::new);

        String url = s3FileUploader.upload(saveDto.getMultipartFile(), DIR_NAME);

        postRepository.save( saveDto.toEntity(url, category) );
    }

    @Transactional(readOnly = true)
    public PostDetailResponse findPostDetailById ( Long id ) {

        return postRepository.findById(id)
            .map(PostDetailResponse::of)
            .orElseThrow(PostNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<PostSimpleResponse> findAllPosts () {

        return postRepository.findAll()
            .stream()
            .map(PostSimpleResponse::of)
            .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<PostSimpleResponse> findAllPostsByCategoryId ( Long id ) {

        Category category = categoryRepository.findById(id)
            .orElseThrow(CategoryNotFoundException::new);

        return postRepository.findByCategory(category)
            .stream()
            .map(PostSimpleResponse::of)
            .collect(Collectors.toList());
    }
    
    @Transactional
    public void updatePost ( Long id ) {
        // TODO: 2021.07.14 -Blue 
    }
}
