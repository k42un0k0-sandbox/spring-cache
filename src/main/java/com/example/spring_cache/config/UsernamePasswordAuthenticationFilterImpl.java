package com.example.spring_cache.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

public class UsernamePasswordAuthenticationFilterImpl extends UsernamePasswordAuthenticationFilter {
    private Logger logger = LoggerFactory.getLogger(UsernamePasswordAuthenticationFilterImpl.class);


    public JsonBody getJsonBody(HttpServletRequest request) {
        JsonBody jsonBody = null;
        try {
            ServletInputStream stream = new HttpServletRequestWrapper(request).getInputStream();
            jsonBody = new ObjectMapper()
                    .readValue(stream, JsonBody.class);
        } catch (IOException e) {
            throw new AuthenticationServiceException("Authentication json can not convert object");
        }
        return jsonBody;
    }

    static private class JsonBody implements Serializable {
        public String username;
        public String password;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        String username;
        String password;
        if (request.getContentType().equals(ContentType.JSON)) {
            JsonBody jsonBody = getJsonBody(request);
            username = jsonBody.username;
            password = jsonBody.password;
        } else {
            username = obtainUsername(request);
            username = (username != null) ? username : "";
            username = username.trim();
            password = obtainPassword(request);
            password = (password != null) ? password : "";
        }
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        logger.info("successful authentication");
        logger.info(getSuccessHandler().toString());
        super.successfulAuthentication(request, response, chain, authentication);
    }
}
