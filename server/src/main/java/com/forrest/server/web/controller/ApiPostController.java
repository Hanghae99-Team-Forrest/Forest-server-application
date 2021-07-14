package com.forrest.server.web.controller;

import com.forrest.server.service.ApiPostService;
import com.forrest.server.util.s3.S3FileUploader;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Created by Bloo
 * @Date: 2021/07/10
 */
@Api
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/posts")
@RestController
public class ApiPostController {

    // TODO: 2021/07/10  Post Controller 구현해주세요
    private final ApiPostService postService;
    private final S3FileUploader s3Uploader;

    @ApiOperation ("이미지 파일 업로드")
    @PostMapping ("")
    public String upload(@RequestPart ("imgFile") MultipartFile multipartFile) throws IOException {
        return s3Uploader.upload(multipartFile, "statc");
    }


    // TODO: 2021.07.14 -Blue 로컬 테스트 후 form data 같이 받도록 변경하기
//    @PostMapping
//    @PreAuthorize("hasAnyAuthority('USER')")
//    public void savePost ( @ModelAttribute PostSaveDto saveDto ) {
//
//        String username = SecurityUtil.getCurrentUsername().get();
//
//    }



    //    @PostMapping("")
//    public ResponseEntity<Void> savePost (
//        @RequestPart("PostSaveDto") PostSaveDto saveDto,
//        @RequestPart(value = "imgFile", required = false) MultipartFile multipartFile ) {
//
//        postService.savePost(saveDto);
//
//        return ResponseEntity.created(URI.create("/api/posts")).build();
//    }
}
