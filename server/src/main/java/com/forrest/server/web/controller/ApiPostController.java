package com.forrest.server.web.controller;

import com.forrest.server.service.ApiPostService;
import com.forrest.server.util.SecurityUtil;
import com.forrest.server.util.s3.S3FileUploader;
import com.forrest.server.web.dto.request.PostSaveDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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


    @ApiOperation ("이미지 파일 업로드 전용")
    @PostMapping ("/img-upload")
    public String upload(@RequestPart ("imgFile") MultipartFile multipartFile) throws IOException {
        return s3Uploader.upload(multipartFile, "static");
    }

    @ApiOperation ("이미지 파일 업로드 + 게시글 정보")
    @PostMapping("")
    public ResponseEntity<Void> savePost ( @ModelAttribute PostSaveDto saveDto ) throws IOException {
        log.info("Post Save Dto >>> {}", saveDto);
        postService.savePost(saveDto);
        return ResponseEntity.created(URI.create("/api/posts")).build();
    }


    // ---------------------------- 테스트 용 ----------------------------

    @ApiOperation ("이미지 파일 업로드 두개로 받아보기")
    @PostMapping("/test")
    public String savePost2 ( @RequestPart("img") MultipartFile multipartFile,  PostSaveDto saveDto ) throws IOException {
        return postService.savePost2(saveDto, multipartFile);
    }

    @ApiOperation ("이미지 파일 업로드 리퀘스트 파람 사용")
    @PostMapping("/test2")
    public String savePost3 ( @RequestPart("img") MultipartFile multipartFile, @RequestParam("name")  String name) throws IOException {

        System.out.println(name);
        System.out.println(multipartFile.toString());
        return name;
    }

}
