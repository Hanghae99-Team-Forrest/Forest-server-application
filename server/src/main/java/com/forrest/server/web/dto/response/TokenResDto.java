package com.forrest.server.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Created by Bloo
 * @Date: 2021/07/13
 */

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenResDto {

    private String token;

}
