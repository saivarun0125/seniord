package com.cengage.WebAssignReleasePlanningTool.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cengage.WebAssignReleasePlanningTool.OrderOfOperations.Release;
import com.cengage.WebAssignReleasePlanningTool.OrderOfOperations.Repository;
import com.cengage.WebAssignReleasePlanningTool.repositories.AssignmentRepository;
import com.cengage.WebAssignReleasePlanningTool.repositories.RepositoryRepository;

@CrossOrigin
@RestController
public class RepositoryController {

    @Autowired
    RepositoryRepository repositoryRepository;

    @GetMapping ( "/repositories" )
    public List<Repository> getRepositories () {

        return repositoryRepository.findAll();
    }

}
