package com.api.org.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.org.model.Facilities;

public interface FacilitiesRepository extends JpaRepository<Facilities, Integer> {
	
	@Query("select f from Facilities f order by f.name asc")
	List<Facilities> getData(Pageable pageable);
	
	boolean existsByName(String name);

}
