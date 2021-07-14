package com.forrest.server.web.controller;

import com.forrest.server.service.ApiPostService;
import com.forrest.server.util.s3.S3FileUploader;
import com.forrest.server.web.dto.request.PostSaveDto;
import com.forrest.server.web.dto.request.PostUpdateDto;
import com.forrest.server.web.dto.response.PostDetailResponse;
import com.forrest.server.web.dto.response.PostSimpleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Created by Bloo
 * @Date: 2021/07/10
 */
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/posts")
@RestController
public class ApiPostController {

    private final ApiPostService postService;
    private final S3FileUploader s3Uploader;

    @PostMapping("")
    public ResponseEntity<Void> savePost ( @ModelAttribute PostSaveDto saveDto ) throws IOException {
        log.info("Post Save Dto >>> {}", saveDto);
        postService.savePost(saveDto);
        return ResponseEntity.created(URI.create("/api/posts")).build();
    }

    /**
     * 전체 조회
     */
    @GetMapping("")
    public ResponseEntity<List<PostSimpleResponse>> findAllPosts() {
        return ResponseEntity.ok().body(postService.findAllPosts());
    }


    /**
     * Post 정보 수정
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePost( @PathVariable Long id, @ModelAttribute PostUpdateDto updateDto ) {
        postService.updatePost(id);
        return ResponseEntity.ok().build();
    }



}
