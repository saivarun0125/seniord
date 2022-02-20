package com.cengage.WebAssignReleasePlanningTool.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cengage.WebAssignReleasePlanningTool.AssignmentRepository;
import com.cengage.WebAssignReleasePlanningTool.Assignment;

@RestController
public class AssignmentController  {

    @Autowired
    AssignmentRepository assignmentRespository;

    @GetMapping("/assignment")
    public List<Assignment> getAssignments(){
        return assignmentRespository.findAll();
    }

    @GetMapping("/assignment/{name}")
    public List<Assignment> getAssignmentById(@PathVariable String name){
        return assignmentRespository.findByName(name);
    }

}
