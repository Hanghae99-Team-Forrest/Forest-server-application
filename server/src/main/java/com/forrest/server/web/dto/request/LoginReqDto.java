package com.forrest.server.web.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * @Created by Bloo
 * @Date: 2021/07/13
 */


@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginReqDto {

    @NotBlank(message = "이메일은 필수 값입니다.")
    @Size(min = 3, max = 50, message = "최소 3자 이상 최대 50자 이하로 작성해주세요")
    //@Email(message = "이메일 형식을 지켜주세요")
    private String username;

    @NotBlank(message = "패스워드는 필수 값입니다.")
    @Size(min = 3, max = 100, message = "최소 3자 이상 최대 100자 이하로 작성해주세요")
    private String password;
}
