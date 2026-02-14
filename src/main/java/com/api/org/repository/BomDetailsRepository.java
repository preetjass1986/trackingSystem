package com.api.org.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.org.model.BomDetails;

public interface BomDetailsRepository extends JpaRepository<BomDetails, Long> {

    void deleteByProjectId(Long projectId);
    
    boolean existsByProjectIdAndMotherPartNoAndChildPartNo(
            Long projectId,
            String motherPartNo,
            String childPartNo
    );

}
