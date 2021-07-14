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
public class PostDetailResponse {

    private String title;
    private String content;
    private String userName;
    private String imgUrl;
    private String postPassword;

    public PostDetailResponse ( String title, String content, String userName, String imgUrl,
        String postPassword ) {
        this.title = title;
        this.content = content;
        this.userName = userName;
        this.imgUrl = imgUrl;
        this.postPassword = postPassword;
    }

    public static PostDetailResponse of ( Post post) {
        return new PostDetailResponse (
            post.getTitle(),
            post.getContent(),
            post.getUserName(),
            post.getImgUrl(),
            post.getPassword()
        );
    }
}
