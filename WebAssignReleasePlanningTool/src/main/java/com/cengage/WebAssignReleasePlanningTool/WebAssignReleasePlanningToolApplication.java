package com.cengage.WebAssignReleasePlanningTool;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cengage.WebAssignReleasePlanningTool.DueDateReport.Assignment;
import com.cengage.WebAssignReleasePlanningTool.repositories.AssignmentRepository;

@SpringBootApplication
public class WebAssignReleasePlanningToolApplication implements CommandLineRunner {
 
    @Autowired
    AssignmentRepository repository;
     
    public static void main(String[] args) {
        SpringApplication.run(WebAssignReleasePlanningToolApplication.class, args);
    }
 
    @Override
    public void run(String... args) throws Exception {
//        List<Assignment> assignment = repository.findByName("Weak Bases");
//        assignment.forEach(item -> System.out.println(item));
//        List<Assignment> assignment2 = repository.findByDateRange("2022-02-28 21:45:00", "2022-03-01 04:00:00");
//        assignment2.forEach(item -> System.out.println(item));
         
    }
}
