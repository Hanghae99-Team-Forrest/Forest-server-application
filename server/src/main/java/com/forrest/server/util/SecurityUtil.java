package com.forrest.server.util;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @Created by Bloo
 * @Date: 2021/07/13
 */

@Slf4j
public class SecurityUtil {

    // 시큐리티 컨텍스트에서 Authentication 객체를 이용해 username 을 리턴해주는 역할
    public static Optional<String> getCurrentUsername() {

        // SecurityContext 에 Authentication 객체가 저장되는 시점은 JwtFilter 의 doFilter 메서드에서
        // Request가 들어올 때 SecuritContext에 Authentication 객체를 저장해서 사용한다.
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            log.debug("Security Context에 인증 정보가 없습니다.");
            return Optional.empty();
        }

        String username = null;
        if (authentication.getPrincipal() instanceof UserDetails ) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            username = springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof String) {
            username = (String) authentication.getPrincipal();
        }

        return Optional.ofNullable(username);
    }
}
