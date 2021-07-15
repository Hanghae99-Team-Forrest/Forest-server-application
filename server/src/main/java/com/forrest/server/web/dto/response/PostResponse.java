package com.forrest.server.web.dto.response;

import com.forrest.server.web.entity.post.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Created by Bloo
 * @Date: 2021/07/14
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class PostResponse {

    private Long postId;
    private Long categoryId;
    private String userName;
    private String title;
    private String content;
    private String password;
    private String imgUrl;

    @Builder
    public PostResponse ( Long postId, Long categoryId, String userName, String title,
        String content, String password, String imgUrl ) {
        this.postId = postId;
        this.categoryId = categoryId;
        this.userName = userName;
        this.title = title;
        this.content = content;
        this.password = password;
        this.imgUrl = imgUrl;
    }

    public static PostResponse of ( Post post ) {
        return PostResponse.builder()
            .postId(post.getId())
            .categoryId(post.getCategory().getId())
            .title(post.getTitle())
            .userName(post.getUserName())
            .content(post.getContent())
            .password(post.getPassword())
            .imgUrl(post.getImgUrl())
            .build();
    }
}
