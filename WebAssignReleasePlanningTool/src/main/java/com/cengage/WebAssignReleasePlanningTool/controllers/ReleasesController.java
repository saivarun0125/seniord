package com.cengage.WebAssignReleasePlanningTool.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cengage.WebAssignReleasePlanningTool.repositories.AssignmentRepository;

@CrossOrigin
@RestController
public class ReleasesController {

    @Autowired
    AssignmentRepository assignmentRespository;

    @GetMapping ( "/releases" )
    public List<Releases> getReleases () {

        return null;
    }

}
