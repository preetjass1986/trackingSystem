package com.api.org.serviceImpl;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.api.org.constants.AppConstants;
import com.api.org.exception.AlreadyExistsException;
import com.api.org.exception.BadRequestException;
import com.api.org.exception.NotAuthorisedException;
import com.api.org.exception.RequiredParameterMissing;
import com.api.org.exception.ResourceNotFoundException;
import com.api.org.model.Users;
import com.api.org.model.UsersSession;
import com.api.org.repository.UserRepository;
import com.api.org.repository.UsersSessionRepository;
import com.api.org.security.JwtTokenProvider;
import com.api.org.security.UserPrincipal;
import com.api.org.service.UserService;
import com.api.org.view.JwtAuthenticationResponse;
import com.api.org.view.LoginRequest;
import com.api.org.view.RegisterRequest;
import com.api.org.view.Response;
import com.api.org.view.UserInfo;

import lombok.RequiredArgsConstructor;


@Service("UserService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UsersSessionRepository usersSessionRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	
	
	
	
	@Override
	@Transactional
	public Response registerUser(RegisterRequest signUpRequest,UserPrincipal userPrincipal) {
		
		if (userPrincipal == null || !Integer.valueOf(AppConstants.SUPPER_ADMIN_ROLE)
		        .equals(userPrincipal.getRole())) {
		    throw new NotAuthorisedException(AppConstants.NOT_AUTHORISED_STRING);
		}
		if (signUpRequest.getUserName() == null || signUpRequest.getUserName().trim().isEmpty()
			     || signUpRequest.getPassword() == null || signUpRequest.getPassword().trim().isEmpty()
			     || signUpRequest.getName() == null || signUpRequest.getName().trim().isEmpty()) {
			        throw new BadRequestException(AppConstants.REQUIRED_PARAMETER_MISSING);
			    }
		if (userRepository.findByUserName(signUpRequest.getUserName()).isPresent()) {
	        throw new AlreadyExistsException(AppConstants.RECORD_ALREDAY_EXISTS_STR);
	    }
					Users user=new Users();
					user.setUserName(signUpRequest.getUserName());
					user.setName(signUpRequest.getName());
					user.setRole(AppConstants.ONE);
					user.setEmail(signUpRequest.getEmail());
					user.setSapId(signUpRequest.getSapId());
					user.setAni(signUpRequest.getMobile());
					user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
					userRepository.save(user);
					
					LoginRequest loginRequest = 
							new LoginRequest(signUpRequest.getUserName(), signUpRequest.getPassword());
					
					JwtAuthenticationResponse jwtAuthenticationResponse = 
							authenticateUser(loginRequest);
					return new Response()
							.setResponseCode(AppConstants.SUCCESS)
							.setMessage(AppConstants.SUCCESS_STR)
							.setData(jwtAuthenticationResponse);	
			
		
	}
	
	@Override
	public Response login(LoginRequest loginRequest) 
	{				
		if (loginRequest.getUsername() == null || loginRequest.getPassword() == null || loginRequest.getUsername().trim().length()<3
				|| loginRequest.getPassword().trim().isEmpty()) { throw new BadRequestException(AppConstants.REQUIRED_PARAMETER_MISSING);}
			JwtAuthenticationResponse jwtAuthenticationResponse =authenticateUser(loginRequest);
			return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR).setData(jwtAuthenticationResponse);
	}
	
	@Override
	public Users getUserDetailByToken(String token) {
		
		Long id=jwtTokenProvider.getUserIdFromJWT(token.replace("Bearer ", ""));	
		if(id!=null)
		{
			Optional<Users> user=userRepository.findById(id);			
			if(user.isPresent())return user.get();
			else {	throw new NotAuthorisedException(AppConstants.NOT_AUTHORISED_STRING);}
		}
		else {	throw new NotAuthorisedException(AppConstants.NOT_AUTHORISED_STRING);}
	
	}
	

	public JwtAuthenticationResponse authenticateUser(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		logger.info("["+loginRequest.getUsername()+"] Name:" + authentication.getName() + "|Auth:" + authentication.getAuthorities());

		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserPrincipal principal = new UserPrincipal();
		principal = (UserPrincipal) authentication.getPrincipal();
		logger.info("["+loginRequest.getUsername()+"] " + principal.toString());
		
		String jwt = jwtTokenProvider.generateToken(authentication);
		Optional<Users> usersOpt=userRepository.findById(principal.getId());
		if(usersOpt.isPresent())
		{
			Users users=usersOpt.get();
			users.setToken(jwt);
			users.setUpdatedOn(new Date());
			userRepository.save(users);			
		}
		
		return new JwtAuthenticationResponse(jwt, principal.getUsername(),usersOpt.get().getRole());
	}	
	
}
