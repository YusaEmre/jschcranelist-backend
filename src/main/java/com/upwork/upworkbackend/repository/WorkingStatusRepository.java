package com.upwork.upworkbackend.repository;

import com.upwork.upworkbackend.model.WorkingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkingStatusRepository extends JpaRepository<WorkingStatus,Long> {
}
