package com.api.org.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.org.model.FacilitiesFlow;

public interface FacilitiesFlowRepository  extends JpaRepository<FacilitiesFlow, Integer> {
	
	@Query("select f from FacilitiesFlow f order by f.name asc")
	List<FacilitiesFlow> getData(Pageable pageable);
	
	boolean existsByProjectIdAndFacilityId(Long projectId,Integer facilityId);
}
