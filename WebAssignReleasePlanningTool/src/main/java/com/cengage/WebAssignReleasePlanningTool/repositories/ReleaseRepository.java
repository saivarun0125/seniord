package com.cengage.WebAssignReleasePlanningTool.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cengage.WebAssignReleasePlanningTool.OrderOfOperations.Release;

@Repository
public interface ReleaseRepository extends JpaRepository<Release, Integer>, ReleaseRepositoryCustom {
	
//	@Query(value="select id, name from releases", nativeQuery=true)
//	public List<Release> findAll();
//	
//	@Query(value="select id, name from releases where id = :id", nativeQuery=true)
//	public List<Release> findById(@Param("id") int id);

	@Modifying
	@Query(value="UPDATE releases set name = :name, updateDate = CURRENT_TIMESTAMP where id = :id", nativeQuery=true)
	public int updateById(@Param("id")int id, @Param("name") String name);
	
	@Transactional
	@Modifying
	@Query(value="INSERT INTO releases (id, name, createDate, updateDate)"
			+ "VALUES (:id, :name, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)", nativeQuery=true)
	public int CreateRelease(@Param("id")int id, @Param("name") String name);
}
