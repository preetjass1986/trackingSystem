package com.api.org.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.org.model.Projects;

public interface ProjectsRepository extends JpaRepository<Projects, Long> {
	
	
	@Query("select p from Projects p order by p.id desc")
	List<Projects> getData(Pageable pageable);
	
	boolean existsByName(String name);

}
