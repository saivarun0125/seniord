package com.cengage.WebAssignReleasePlanningTool.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.cengage.WebAssignReleasePlanningTool.OrderOfOperations.RepositoryReleaseAction;

public interface RepositoryReleaseActionRepository extends JpaRepository<RepositoryReleaseAction, Integer> {

    @Override
    @Query ( value = "select id, repositoryId, repoAction from repositoryreleaseaction", nativeQuery = true )
    public List<RepositoryReleaseAction> findAll ();

    @Query ( value = "select id, repositoryId, repoAction from repositoryreleaseaction where id = :id",
            nativeQuery = true )
    public List<RepositoryReleaseAction> findById ( @Param ( "id" ) int id );

    @Transactional
    @Modifying
    @Query ( value = "UPDATE repositoryreleaseaction set repositoryId = :repositoryId, repoAction = :repoAction, updateDate = CURRENT_TIMESTAMP where id = :id",
            nativeQuery = true )
    public int updateById ( @Param ( "id" ) final int id, @Param ( "repositoryId" ) int repositoryId,
            @Param ( "repoAction" ) String repoAction );

    @Transactional
    @Modifying
    @Query ( value = "INSERT INTO repositoryreleaseaction (id, repositoryId, repoAction, createDate, updateDate)"
            + "VALUES (:id, :repositoryId, :repoAction CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)", nativeQuery = true )
    public int CreateReleaseAction ( @Param ( "id" ) int id, @Param ( "repositoryId" ) int repositoryId,
            @Param ( "repoAction" ) String repoAction );
}
