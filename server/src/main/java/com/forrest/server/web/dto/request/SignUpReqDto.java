package com.forrest.server.web.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * @Created by Bloo
 * @Date: 2021/07/11
 */
@Builder
@AllArgsConstructor
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

}
