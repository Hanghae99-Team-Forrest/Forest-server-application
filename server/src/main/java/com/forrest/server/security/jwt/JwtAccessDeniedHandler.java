package com.forrest.server.security.jwt;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

/**
 * @Created by Bloo
 * @Date: 2021/07/13
 */

// 역할: 필요한 권한이 존재하지 않는 경우 403 에러를 리턴한다
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle( HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, IOException {
        //필요한 권한이 없이 접근하려 할때 403
        response.sendError(HttpServletResponse.SC_FORBIDDEN);
    }
}
