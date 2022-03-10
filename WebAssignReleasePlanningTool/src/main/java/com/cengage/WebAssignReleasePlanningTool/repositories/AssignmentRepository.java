package com.cengage.WebAssignReleasePlanningTool.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cengage.WebAssignReleasePlanningTool.DueDateReport.Assignment;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {
    
    @Query(value = "select a.id, cat.name as category, d.begins as start_date, d.due as end_date, datediff(d.due,d.begins) as days_available, 0 as roster_count "
    		+ "from assignments a, categories cat, deployments d, sections s "
    		+ "where a.category=cat.id and d.assignment=a.id "
    		+ "and d.section=s.id", nativeQuery = true)
	public List<Assignment> findAll();
	
    @Query(value = "select a.id, cat.name as category, d.begins as start_date, d.due as end_date, "
    			 + "datediff(d.due,d.begins) as days_available, count(r.user) as roster_count "
    			 + "from assignments a, categories cat, deployments d, sections s, roster r "
    			 + "where a.category=cat.id and d.assignment=a.id "
    			 + "and d.section=s.id and s.id=r.section "
    		     + "and a.name = :name", nativeQuery = true)
    public List<Assignment> findByName(@Param("name") String name);
    
    @Query(value = "SELECT a.id, cat.name as category, d.begins as start_date, d.due as end_date, datediff(d.due, d.begins) as days_available, count(r.user) as roster_count "
    		+ "FROM deployments d, assignments as a, categories as cat, sections as s, roster as r "
    		+ "WHERE ((d.begins >= :start and d.due <= :end) "
    		+ "or (d.begins <= :start and d.due >= :start and d.due <= :end) "
    		+ "or (d.begins >= :start and d.begins <= :end and d.due >= :end)) "
    		+ "and a.id=d.assignment "
    		+ "and a.category = cat.id "
    		+ "and d.section = s.id "
    		+ "and s.id = r.section "
    		+ "GROUP BY r.section", nativeQuery = true)
    public List<Assignment> findByDateRange(@Param("start") String start, @Param("end") String end);
}