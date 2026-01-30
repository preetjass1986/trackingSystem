package com.api.org.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.api.org.model.Users;
import com.api.org.view.UserInfo;


@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

	
	List<Users> findByIdIn(List<Long> userIds);
			
	List<Users> findByRole(Integer role);
	
	Optional<Users> findByAni(String ani);
	
	Optional<Users> findByUserName(String userName);
	
	
	List<Users> findByAni(String ani, Pageable page);

	


	
}

