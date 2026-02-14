package com.api.org.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.org.model.Stages;

public interface StagesRepository extends JpaRepository<Stages, Integer> {
	
	@Query("select s from Stages s order by s.name asc")
	List<Stages> getData(Pageable pageable);
	
	@Query("select s from Stages s where s.facilityId=:facilityId and s.status=:status order by s.name asc")
	List<Stages> getData(Integer facilityId,Integer status, Pageable pageable);
	
	boolean existsByName(String name);

}
