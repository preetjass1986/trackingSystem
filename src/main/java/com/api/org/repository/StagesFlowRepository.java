package com.api.org.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.org.model.Stages;
import com.api.org.model.StagesFlow;

public interface StagesFlowRepository extends JpaRepository<StagesFlow, Integer> {
	
	@Query("select s from StagesFlow s order by s.id desc")
	List<StagesFlow> getData(Pageable pageable);
	
	@Query("select s from StagesFlow s where s.projectId=:projectId and s.facilityId=:facilityId and s.status=:status order by s.id asc")
	List<StagesFlow> getData(Long projectId,Integer facilityId,Integer status, Pageable pageable);
	
	boolean existsByProjectIdAndFacilityIdAndStageId(Long projectId,Integer FacilityId,Integer StatgeId);

}
