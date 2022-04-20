package com.cengage.WebAssignReleasePlanningTool.controllers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import com.cengage.WebAssignReleasePlanningTool.OrderOfOperations.ReleaseAction;
import com.cengage.WebAssignReleasePlanningTool.OrderOfOperations.Repository;
import com.cengage.WebAssignReleasePlanningTool.repositories.ReleaseRepository;

@CrossOrigin
@RestController
public class ReleaseController {

	@Autowired
	ReleaseRepository releaseRepository;
	
    @GetMapping ( "/release" )
    public List<Map<String, Object>> getReleases () {

    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<Release> releases = releaseRepository.findAll();
        
        //return just name and id
        for(Release r : releases)
        {
        	Map<String, Object> map = new LinkedHashMap<String, Object>();
        	map.put("name", r.getName());
        	map.put("id", r.getId());
        	
        	list.add(map);
        }
        
        return list;
    }
    
    @GetMapping("/release/{id}")
    public Map<String, Object> getRelease(@PathVariable int id)
    {
    	Map<String, Object> map = new LinkedHashMap<String, Object>();
    	Release release = releaseRepository.findById(id).get(0);
    
    	map.put("name", release.getName());
    	
    	List<Map<String, Object>> releaseActions = new ArrayList<Map<String, Object>>();
    	
    	//add each action to the output
    	for(ReleaseAction ra : release.getReleaseActions())
    	{
    		Map<String, Object> raMap = new LinkedHashMap<String, Object>();
    		raMap.put("name", ra.getName());
    		raMap.put("id", ra.getId());
    		raMap.put("isSelected", ra.isSelected());
    		releaseActions.add(raMap);
    	}
    	map.put("releaseActions", releaseActions);
    	
    	List<Map<String, Object>> rollbackActions = new ArrayList<Map<String, Object>>();
    	//and the rollback plan
    	for(ReleaseAction ra : release.getRollbackActions())
    	{
    		Map<String, Object> rbMap = new LinkedHashMap<String, Object>();
    		rbMap.put("name", ra.getName());
    		rbMap.put("id", ra.getId());
    		rbMap.put("isSelected", ra.isSelected());
    		rollbackActions.add(rbMap);
    	}
    	map.put("rollbackActions", rollbackActions);
    	map.put("duration", release.getDuration());
    	
    	return map;
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
