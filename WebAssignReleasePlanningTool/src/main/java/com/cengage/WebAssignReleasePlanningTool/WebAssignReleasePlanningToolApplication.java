package com.cengage.WebAssignReleasePlanningTool;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cengage.WebAssignReleasePlanningTool.DueDateReport.Assignment;
import com.cengage.WebAssignReleasePlanningTool.OrderOfOperations.Repository;
import com.cengage.WebAssignReleasePlanningTool.repositories.AssignmentRepository;
import com.cengage.WebAssignReleasePlanningTool.repositories.RepositoryRepository;

@SpringBootApplication
public class WebAssignReleasePlanningToolApplication implements CommandLineRunner {
 
    @Autowired
    AssignmentRepository repository;
    
    @Autowired
    RepositoryRepository reprep;
     
    public static void main(String[] args) {
        SpringApplication.run(WebAssignReleasePlanningToolApplication.class, args);
    }
 
    @Override
    public void run(String... args) throws Exception {
        List<Repository> test = reprep.findAll();
        for(Repository r : test)
        {
        	System.out.print(r.getName() + " deps: ");
        	for(Repository d : r.getDependents())
        	{
        		System.out.print(d.getName() + " ");
        	}
        	
        	System.out.println();
        }
        
    }
}
