package com.api.org.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);
    @Override
	public void commence(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,AuthenticationException e) throws IOException, ServletException {
        logger.error("Responding with unauthorized error. Message - {}", e.getMessage()+"::"+e.getCause());
        //httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
        Response(httpServletResponse,e.getMessage());
    }
    
    private void Response(HttpServletResponse httpServletResponse,String message) throws JsonGenerationException, JsonMappingException, IOException
    {
    	 httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
         httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

         final Map<String, Object> body = new HashMap<>();
         body.put("message", message);
         final ObjectMapper mapper = new ObjectMapper();
         
         mapper.writeValue(httpServletResponse.getOutputStream(), body);
    }
}
