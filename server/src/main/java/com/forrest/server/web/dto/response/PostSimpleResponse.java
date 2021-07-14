package com.forrest.server.web.dto.response;

import com.forrest.server.web.entity.post.Post;
import javafx.geometry.Pos;
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
public class PostSimpleResponse {

    private Long postId;
    private Long categoryId;
    private String userName;
    private String title;
    private String content;
    private String imgUrl;

    @Builder
    public PostSimpleResponse ( Long postId, Long categoryId, String userName, String title, String content, String imgUrl ) {
        this.postId = postId;
        this.categoryId = categoryId;
        this.userName = userName;
        this.title = title;
        this.content = content;
        this.imgUrl = imgUrl;
    }


    public static PostSimpleResponse of ( Post post ) {
        return PostSimpleResponse.builder()
            .postId(post.getId())
            .categoryId(post.getCategory().getId())
            .title(post.getTitle())
            .userName(post.getUserName())
            .content(post.getContent())
            .imgUrl(post.getImgUrl())
            .build();
    }
}
