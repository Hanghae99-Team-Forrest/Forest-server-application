package com.forrest.server.web.dto.request;

import com.forrest.server.web.entity.category.Category;
import com.forrest.server.web.entity.post.Post;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Created by Bloo
 * @Date: 2021/07/14
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class PostSaveDto {

    private Long categoryId;

    private String userName;

    private String title;

    private String content;

    @NotBlank(message = "게시글 저장 패스워드는 필수 값입니다.")
    private String postPassword;

    private MultipartFile multipartFile;

    // TODO: 2021.07.14 -Blue
    // post 저장할 때 지금은 이미지 1개니까 대충 이미지 url 을 박아주자
    public Post toEntity(String imgUrl, Category category ) {
       return Post.builder()
           .title(title)
           .userName(userName)
           .content(content)
           .imgUrl(imgUrl)
           .password(postPassword)
           .category(category)
           .build();
    }
}
