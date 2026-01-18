package com.api.org.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.org.model.UsersLog;

public interface UsersLogRepository extends JpaRepository<UsersLog, Long> {

}
