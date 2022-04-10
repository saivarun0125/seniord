package com.cengage.WebAssignReleasePlanningTool.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cengage.WebAssignReleasePlanningTool.DueDateReport.Assignment;
import com.cengage.WebAssignReleasePlanningTool.OrderOfOperations.Repository;

public interface RepositoryRepository extends JpaRepository<Repository, Integer>, RepositoryRepositoryCustom {
}
