package com.api.org.serviceImpl;

import java.util.Date;
import java.util.Optional;
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


@Service("UserService")
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
	public Response registerUser(RegisterRequest signUpRequest,UserPrincipal userPrincipal) {
		if(userPrincipal.getRoles()==AppConstants.SUPPER_ADMIN_ROLE)
		{
			if(signUpRequest.getUsername()!=null && signUpRequest.getPassword()!=null && signUpRequest.getName()!=null) {
				Optional<Users> users=userRepository.findByAni(signUpRequest.getUsername());
				if(users.isPresent())throw new AlreadyExistsException(AppConstants.RECORD_ALREDAY_EXISTS_STR);
				else
				{
					Users user=new Users();
					user.setAni(signUpRequest.getUsername());
					user.setCreatedOn(new Date());
					user.setUserName(signUpRequest.getName());
					user.setRole(AppConstants.ONE);
					user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
					userRepository.save(user);
					LoginRequest loginRequest = new LoginRequest(signUpRequest.getUsername(), signUpRequest.getPassword());
					JwtAuthenticationResponse jwtAuthenticationResponse = authenticateUser(loginRequest);
					return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR).setData(jwtAuthenticationResponse);	
				}
			
			}else {throw new BadRequestException(AppConstants.REQUIRED_PARAMETER_MISSING);}
		}else { throw new BadRequestException(AppConstants.NOT_AUTHORISED_STRING);}
	}
	
	@Override
	public Response login(LoginRequest loginRequest) 
	{				
		if(loginRequest.getUsername()!=null && loginRequest.getUsername().length()>2) {
			JwtAuthenticationResponse jwtAuthenticationResponse = authenticateUser(loginRequest);
			return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR).setData(jwtAuthenticationResponse);
		}
		else { throw new BadRequestException(AppConstants.REQUIRED_PARAMETER_MISSING);}

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
			users.setUpdatedOn(new Date());
			userRepository.save(users);			
		}
		// ############## Update auth token in users table ############
		if(jwt!=null && jwt.length()>1)
		{
			UsersSession usersSession=new UsersSession();
			usersSession.setUserid(principal.getId());
			usersSession.setCreatedOn(new Date());
			usersSession.setToken(jwt);
			usersSessionRepository.save(usersSession);	
		}     
		
		return new JwtAuthenticationResponse(jwt, principal.getUsername(),usersOpt.get().getRole());

	}

	
	
}
