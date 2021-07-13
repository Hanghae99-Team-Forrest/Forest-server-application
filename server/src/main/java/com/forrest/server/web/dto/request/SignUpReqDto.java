package com.forrest.server.web.dto.request;

import com.forrest.server.util.enumclass.UserRole;
import com.forrest.server.web.entity.user.User;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * @Created by Bloo
 * @Date: 2021/07/11
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class SignUpReqDto {

    @NotBlank(message = "Email은 필수 값입니다")
    private String username;

    @NotBlank(message = "패스워드는 필수 값입니다.")
    private String password;

    @NotBlank(message = "Nick Name은 필수 값입니다.")
    private String nickName;
    //private String profileImg

    @Builder
    public SignUpReqDto ( String email, String password, String nickName ) {
        this.username = email;
        this.password = password;
        this.nickName = nickName;
    }

    // 일반 회원가입
    public User toEntity(String encodedPassword) {
        return User.builder()
            .username(username)
            .password(encodedPassword)
            .nickName(nickName)
            .activated(true)
            .build();
    }
}
