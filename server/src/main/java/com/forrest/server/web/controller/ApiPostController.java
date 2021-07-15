package com.forrest.server.web.controller;

import com.forrest.server.service.ApiPostService;
import com.forrest.server.web.dto.request.PostSaveDto;
import com.forrest.server.web.dto.request.PostUpdateDto;
import com.forrest.server.web.dto.response.PostResponse;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("")
    public ResponseEntity<Void> savePost ( @ModelAttribute PostSaveDto saveDto ) throws IOException {
        log.info("Post Save Dto >>> {}", saveDto);
        postService.savePost(saveDto);
        return ResponseEntity.created(URI.create("/api/posts")).build();
    }

    @GetMapping("")
    public ResponseEntity<List<PostResponse>> findAllPosts() {
        return ResponseEntity.ok().body(postService.findAllPosts());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePost( @PathVariable Long id, @ModelAttribute PostUpdateDto updateDto ) throws IOException {
        postService.updatePost(id, updateDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost( @PathVariable Long id ) {
        postService.deletePostById(id);
        return ResponseEntity.ok().build();
    }
}
