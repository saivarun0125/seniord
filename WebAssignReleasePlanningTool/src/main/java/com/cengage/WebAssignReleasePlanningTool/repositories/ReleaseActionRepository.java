package com.cengage.WebAssignReleasePlanningTool.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cengage.WebAssignReleasePlanningTool.OrderOfOperations.ReleaseAction;

public interface ReleaseActionRepository extends JpaRepository<ReleaseAction, Integer> {

    @Override
    @Query ( value = "select 0 as dtype, name, id, releaseId, rollback, isSelected, notes, duration from releaseaction",
            nativeQuery = true )
    public List<ReleaseAction> findAll ();

    @Query ( value = "select 0 as dtype, name, id, releaseId, rollback, isSelected, notes, duration from releaseaction where id = :id",
            nativeQuery = true )
    public List<ReleaseAction> findById ( @Param ( "id" ) int id );

    @Transactional
    @Modifying
    @Query ( value = "UPDATE releaseaction set name = :name, releaseId = :releaseId, rollback = :rollback, isSelected = :isSelected, notes = :notes, duration = :duration, updateDate = CURRENT_TIMESTAMP where id = :id",
            nativeQuery = true )
    public int updateById ( @Param ( "id" ) int id, @Param ( "releaseId" ) int releaseId,
            @Param ( "rollback" ) int rollback, @Param ( "isSelected" ) boolean isSelected,
            @Param ( "notes" ) String notes, @Param ( "duration" ) int duration,
            @Param ( "name" ) String name);

    @Transactional
    @Modifying
    @Query ( value = "INSERT INTO releaseaction (id, name, releaseId, rollback, isSelected, notes, duration, createDate, updateDate)"
            + "VALUES (:id, :name, :releaseId, :rollback, :isSelected, :notes, :duration, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)",
            nativeQuery = true )
    public int CreateReleaseAction ( @Param ( "id" ) int id, @Param ( "releaseId" ) int releaseId,
            @Param ( "rollback" ) int rollback, @Param ( "isSelected" ) boolean isSelected,
            @Param ( "notes" ) String notes, @Param ( "duration" ) int duration,
            @Param ( "name" ) String name);
}
