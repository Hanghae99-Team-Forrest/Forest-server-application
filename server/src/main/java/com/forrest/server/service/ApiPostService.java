package com.forrest.server.service;

import com.forrest.server.util.exception.CategoryNotFoundException;
import com.forrest.server.util.exception.PostNotFoundException;
import com.forrest.server.util.s3.S3FileUploader;
import com.forrest.server.web.dto.request.PostSaveDto;
import com.forrest.server.web.dto.request.PostUpdateDto;
import com.forrest.server.web.dto.response.PostResponse;
import com.forrest.server.web.entity.category.Category;
import com.forrest.server.web.entity.category.CategoryRepository;
import com.forrest.server.web.entity.post.Post;
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

    @Transactional
    public void savePost ( PostSaveDto saveDto ) throws IOException {
        Category category = findCategoryById(saveDto.getCategoryId());
        String url = s3FileUploader.upload(saveDto.getMultipartFile(), DIR_NAME);
        postRepository.save( saveDto.toEntity(url, category) );
    }

    @Transactional(readOnly = true)
    public List<PostResponse> findAllPosts () {
        return postRepository.findAll()
            .stream()
            .map(PostResponse::of)
            .collect(Collectors.toList());
    }

    @Transactional
    public void updatePost ( Long postId, PostUpdateDto updateDto ) throws IOException {

        Category category = findCategoryById(updateDto.getCategoryId());
        Post post = findPostById(postId);

        if (updateDto.getMultipartFile() != null) {
            removeImg(post.getImgUrl());
            updateImg(post, updateDto.getMultipartFile());
        }

        post.update(updateDto, category);
    }

    @Transactional
    public void deletePostById ( Long id ) {
        Post post = findPostById(id);
        removeImg(post.getImgUrl());
        postRepository.delete(post);
    }

    // 이미지 제거
    private void removeImg ( String imgUrl ) {
        s3FileUploader.removeImg(imgUrl);
    }

    // 이미지 수정
    private void updateImg( Post post, MultipartFile multipartFile ) throws IOException {
        post.updateImgUrl(s3FileUploader.upload(multipartFile, DIR_NAME));
    }

    // 카테고리 조회
    private Category findCategoryById ( Long categoryId ) {
        return categoryRepository.findById(categoryId)
            .orElseThrow(CategoryNotFoundException::new);
    }

    // 게시글 조회
    private Post findPostById ( Long postId ) {
        return postRepository.findById(postId)
            .orElseThrow(PostNotFoundException::new);
    }
}
