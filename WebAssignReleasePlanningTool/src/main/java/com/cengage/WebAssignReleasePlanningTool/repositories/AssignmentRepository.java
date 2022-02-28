package com.cengage.WebAssignReleasePlanningTool.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cengage.WebAssignReleasePlanningTool.DueDateReport.Assignment;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {
    
    @Query(value = "select a.id, cat.name as category, d.begins as start_date, d.ends as end_date, "
    			 + "datediff(d.due,d.begins) as days_available, count(r.user) as roster_count "
    			 + "from assignments a, categories cat, deployments d, sections s, roster r "
    			 + "where a.category=cat.id and d.assignment=a.id "
    			 + "and d.section=s.id and s.id=r.section "
    		     + "and a.name = :name", nativeQuery = true)
    public List<Assignment> findByName(@Param("name") String name);
    
}