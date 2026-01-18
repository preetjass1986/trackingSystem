package com.api.org.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.io.CharStreams;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Interceptor implements HandlerInterceptor {
	
	
@Override
public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
	
	/* String body="";
	   if ("POST".equalsIgnoreCase(request.getMethod())) {
	        body = CharStreams.toString(request.getReader());
	    }
	log.info("Request -> Url : "+request.getRequestURI() +"  Method : "+request.getMethod()+"  Data : "+body);*/
	log.info("Request -> Url : "+request.getRequestURI() +"  Method : "+request.getMethod());
	return true;
	/*if(request.getRequestURI().contains("swagger")) return true;
	else if(request.getHeader("x-auth")!=null && request.getHeader("x-auth").equals("3ac074dc2692d2feb7145ccb180bd1c0c59f5874")) return true;
	else 
	{		
		response.setStatus(404);
		return false;
	}*/
}

@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	    log.info("Response -> status : "+response.getStatus());
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}


}
