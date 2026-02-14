package com.api.org.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final long MAX_AGE_SECS = 3600;
    
    @Autowired
	Interceptor interceptor;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
                .maxAge(MAX_AGE_SECS);
    }
    
    private ObjectMapper mapper;

    @Autowired  // spring.jackson.* ObjectMapper's config
    public WebMvcConfig(ObjectMapper mapper) {
        this.mapper = mapper;
    }
    
    @Override
    public void extendMessageConverters (List<HttpMessageConverter<?>> converters) {
        converters.stream()
                .filter(x -> x instanceof  MappingJackson2HttpMessageConverter)
                .forEach(x -> ((MappingJackson2HttpMessageConverter) x).setObjectMapper(mapper));
    }
}
