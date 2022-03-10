package com.cengage.WebAssignReleasePlanningTool.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cengage.WebAssignReleasePlanningTool.DueDateReport.Assignment;
import com.cengage.WebAssignReleasePlanningTool.repositories.AssignmentRepository;

@RestController
public class AssignmentController  {

    @Autowired
    AssignmentRepository assignmentRespository;

    @GetMapping("/ReleaseWindows")
    public List<Assignment> getAssignments(){
        return assignmentRespository.findByDateRange("2022-02-28 21:45:00", "2022-03-01 04:00:00");
    }

    @GetMapping("/assignment/{name}")
    public List<Assignment> getAssignmentByName(@PathVariable String name){
        return assignmentRespository.findByName(name);
    }

}
