package com.example.spring_cache.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationSuccessHandlerImpl extends SavedRequestAwareAuthenticationSuccessHandler {
    private Logger logger = LoggerFactory.getLogger(AuthenticationSuccessHandlerImpl.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String contentType = request.getContentType();
        logger.info("content-type: " + contentType);
        switch (contentType) {
            case ContentType.JSON:
                response.setContentType(ContentType.JSON);
                response.setStatus(HttpServletResponse.SC_OK);
                ObjectMapper mapper = new ObjectMapper();
                Object res = new Object() {
                    public final String status = "ok";
                };
                response.getWriter().print(mapper.writeValueAsString(res));
                break;
            default:
                handle(request, response, authentication);
        }
        logger.info("content-type: " + contentType);
    }
}