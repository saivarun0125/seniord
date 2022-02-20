package com.cengage.WebAssignReleasePlanningTool.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cengage.WebAssignReleasePlanningTool.models.Assignment;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {
    
    @Query(value = "select a.id, a.name, cat.name as category from assignments a, categories cat where a.category=cat.id and a.name = :name", nativeQuery = true)
    public List<Assignment> findByName(@Param("name") String name);
    
}