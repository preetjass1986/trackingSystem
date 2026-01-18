package com.api.org.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.api.org.model.UsersSession;

public interface UsersSessionRepository extends JpaRepository<UsersSession, Long> {
	
	Boolean existsByUseridAndToken(Long userid,String token);
    
    @Transactional
	@Modifying
	@Query("delete from UsersSession where  userid=:userid")
	void deleteByUserid(Long userid);

}
