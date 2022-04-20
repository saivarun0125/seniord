package com.cengage.WebAssignReleasePlanningTool;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cengage.WebAssignReleasePlanningTool.DueDateReport.Assignment;
import com.cengage.WebAssignReleasePlanningTool.OrderOfOperations.Release;
import com.cengage.WebAssignReleasePlanningTool.OrderOfOperations.ReleaseAction;
import com.cengage.WebAssignReleasePlanningTool.OrderOfOperations.Repository;
import com.cengage.WebAssignReleasePlanningTool.repositories.AssignmentRepository;
import com.cengage.WebAssignReleasePlanningTool.repositories.ReleaseRepository;
import com.cengage.WebAssignReleasePlanningTool.repositories.RepositoryRepository;

@SpringBootApplication
public class WebAssignReleasePlanningToolApplication implements CommandLineRunner {
 
    @Autowired
    ReleaseRepository repository;
    public static void main(String[] args) {
        SpringApplication.run(WebAssignReleasePlanningToolApplication.class, args);
    }
 
    @Override
    public void run(String... args) throws Exception {
        List<Release> test = repository.findById(0);
        
        for(Release r : test)
        {
        	System.out.println("Release: " + r.getName());
        	for(ReleaseAction ra : r.getReleaseActions())
        	{
        		System.out.println("\tAction: " + ra.getName());
        	}
        	
        	for(ReleaseAction ra : r.getRollbackActions())
        	{
        		System.out.println("\tRollback: " + ra.getName());
        	}
        	
        	System.out.println();
        }
        
    }
}
