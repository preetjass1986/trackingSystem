package com.api.org;

import java.util.TimeZone;
import java.util.logging.LogManager;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.api.org.constants.AppConstants;

import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
//@EnableSwagger2
public class Application extends SpringBootServletInitializer {
	
	private static Class<Application> applicationClass = Application.class;
	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);
	}
	
	@PostConstruct
	public void started() {
		TimeZone.setDefault(TimeZone.getTimeZone(AppConstants.TIMEZONE));
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }
	
	

}
