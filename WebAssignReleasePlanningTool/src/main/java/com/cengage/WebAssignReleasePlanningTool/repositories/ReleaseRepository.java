package com.cengage.WebAssignReleasePlanningTool.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cengage.WebAssignReleasePlanningTool.OrderOfOperations.Release;

public interface ReleaseRepository extends JpaRepository<Release, Integer>{
	
	@Query(value="select id, name from releases", nativeQuery=true)
	public List<Release> findAll();
	
	@Query(value="select id, name from releases where id = :id", nativeQuery=true)
	public List<Release> findById(@Param("id") int id);
}
