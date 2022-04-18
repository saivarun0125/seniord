package com.cengage.WebAssignReleasePlanningTool.controllers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    public List<Map<String, Object>> getRepositories ()
    {
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<Repository> repo = repositoryRepository.findAll();
    
        //add name and id for each repo
        for(Repository r : repo)
        {
        	Map<String, Object> map = new LinkedHashMap<String, Object>();
        	map.put("id", r.getId());
        	map.put("name", r.getName());
        	list.add(map);
        }
        
        return list;
    }

}
