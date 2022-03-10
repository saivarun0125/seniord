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

    @GetMapping("/releaseWindows")
    public List<Assignment> getAssignmentByRange(String startDate, String endDate, int duration){
        return assignmentRespository.findByDateRange(startDate, endDate);
    }

    @GetMapping("/assignment/{name}")
    public List<Assignment> getAssignmentByName(@PathVariable String name){
        return assignmentRespository.findByName(name);
    }

}
