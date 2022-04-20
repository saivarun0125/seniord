package com.cengage.WebAssignReleasePlanningTool.repositories;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.cengage.WebAssignReleasePlanningTool.OrderOfOperations.Release;
import com.cengage.WebAssignReleasePlanningTool.OrderOfOperations.ReleaseAction;
import com.cengage.WebAssignReleasePlanningTool.OrderOfOperations.RepositoryReleaseAction;

public interface ReleaseRepositoryCustom {

    public List<Release> findAll ();
    
	public List<Release> findById(@Param("id") int id);
}
