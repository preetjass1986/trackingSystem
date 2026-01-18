package com.api.org.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.org.model.ComponentsMaster;

public interface ComponentsMasterRepository extends JpaRepository<ComponentsMaster, Long> {
	
	@Query("select cm from ComponentsMaster cm order by cm.id desc")
	List<ComponentsMaster> getData(Pageable pageable);
	
	boolean existsByName(String name);


}
