package com.cengage.WebAssignReleasePlanningTool.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cengage.WebAssignReleasePlanningTool.OrderOfOperations.RepositoryReleaseAction;
import com.cengage.WebAssignReleasePlanningTool.repositories.RepositoryReleaseActionRepository;

@CrossOrigin
@RestController
public class repositoryReleaseActionController {

    @Autowired
    RepositoryReleaseActionRepository repositoryReleaseActionRepository;

    @GetMapping ( "/repositoryreleaseaction" )
    public List<RepositoryReleaseAction> getRepositoryReleaseActions () {

        return RepositoryReleaseAction.findAll();
    }

}
