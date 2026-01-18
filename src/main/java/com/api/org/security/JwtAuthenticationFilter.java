package com.api.org.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;


import com.api.org.model.Users;
import com.api.org.repository.UsersSessionRepository;
import com.api.org.serviceImpl.CustomUserDetailsService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    
    @Autowired
    private  UsersSessionRepository usersSessionRepository;

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
        	
            String jwt = getJwtFromRequest(request);
           // logger.info("jwt="+jwt);
            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                Long userId = tokenProvider.getUserIdFromJWT(jwt);

                /*
                    Note that you could also encode the user's username and roles inside JWT claims
                    and create the UserDetails object by parsing those claims from the JWT.
                    That would avoid the following database hit. It's completely up to you.
                 */
               // User user=customUserDetailsService.getUserById(userId);
                Users user = customUserDetailsService.loadUserByUserId(userId);
                //if(user!=null && user.getAuthToken()!=null && user.getAuthToken().equalsIgnoreCase(jwt)) // if same token present in users table then allow
                if(user!=null) 
                {
                	if(usersSessionRepository.existsByUseridAndToken(userId, jwt))// if same token present in users table then allow
                	{
	                	UserDetails userDetails=UserPrincipal.create(user);
		                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));	
		                SecurityContextHolder.getContext().setAuthentication(authentication);
                	}
                }
            }
        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");        
        if(bearerToken==null) 
        {  
        	// only for websocket connection from angular or any other javascript 
        	//if(request.getHeader("Sec-WebSocket-Protocol")!=null)bearerToken = request.getHeader("Sec-WebSocket-Protocol").replace("soap, ","");        	
        	if(bearerToken==null)
        	{
        	   try {bearerToken=request.getParameter("wst").replace("%22","");}catch(Exception ex) {}   
        	}
        	return bearerToken;
        }
       
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}
