package com.cengage.WebAssignReleasePlanningTool.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cengage.WebAssignReleasePlanningTool.DueDateReport.Assignment;
import com.cengage.WebAssignReleasePlanningTool.repositories.AssignmentRepository;

@CrossOrigin
@RestController
public class AssignmentController  {

    @Autowired
    AssignmentRepository assignmentRespository;

    @GetMapping("/assignment")
    public List<Assignment> getAssignments(){
        return assignmentRespository.findAll();
    }

    @GetMapping("/assignment/{name}")
    public List<Assignment> getAssignmentByName(@PathVariable String name){
        return assignmentRespository.findByName(name);
    }

}
