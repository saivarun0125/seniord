package com.cengage.WebAssignReleasePlanningTool.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cengage.WebAssignReleasePlanningTool.models.Assignment;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {
    
    @Query(value = "SELECT * FROM assignments a WHERE a.name = :name", nativeQuery = true)
    public List<Assignment> findByName(@Param("name") String name);
    
}