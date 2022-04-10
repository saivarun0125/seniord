package com.cengage.WebAssignReleasePlanningTool.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cengage.WebAssignReleasePlanningTool.OrderOfOperations.Release;
import com.cengage.WebAssignReleasePlanningTool.OrderOfOperations.Repository;
import com.cengage.WebAssignReleasePlanningTool.repositories.ReleaseRepository;

@CrossOrigin
@RestController
public class ReleaseController {

	@Autowired
	ReleaseRepository releaseRepository;
	
    @GetMapping ( "/release" )
    public List<Release> getReleases () {

        return releaseRepository.findAll();
    }
    
    @GetMapping("/release/{id}")
    public List<Release> getRelease(@PathVariable int id)
    {
    	return releaseRepository.findById(id);
    }
    
    @Modifying
    @PutMapping("/release/{id}")
    public void updateRelease(@PathVariable int id, @RequestParam String name )
    {
    	releaseRepository.updateById(id, name);
    	System.out.println("Release " + id + " updated!");
    }
    
    @Modifying
    @PostMapping("/release/{id}")
    public void createRelease(@PathVariable int id, @RequestParam String name)
    {
    	releaseRepository.CreateRelease(id, name);
    	System.out.println("Release " + name + " created!");
    }
}
