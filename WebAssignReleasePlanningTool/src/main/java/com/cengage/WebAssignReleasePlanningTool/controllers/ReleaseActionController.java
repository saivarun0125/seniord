package com.cengage.WebAssignReleasePlanningTool.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cengage.WebAssignReleasePlanningTool.OrderOfOperations.Release;
import com.cengage.WebAssignReleasePlanningTool.OrderOfOperations.ReleaseAction;
import com.cengage.WebAssignReleasePlanningTool.repositories.ReleaseActionRepository;
import com.cengage.WebAssignReleasePlanningTool.repositories.ReleaseRepository;

public class ReleaseActionController {

    @Autowired
    ReleaseActionRepository releaseActionRepository;

    @GetMapping ( "/release/action" )
    public List<ReleaseAction> getReleaseActions () {

        return releaseActionRepository.findAll();
    }

    @GetMapping ( "/release/{id}" )
    public List<ReleaseAction> getReleaseAction ( @PathVariable final int id ) {
        return releaseActionRepository.findById( id );
    }

    @Modifying
    @PutMapping ( "/release/action/{id}" )
    public void updateReleaseAction ( @PathVariable final int id, @RequestParam final String name ) {
        releaseActionRepository.updateById( id, id, id, false, name, id );
        System.out.println( "ReleaseAction " + id + " updated!" );
    }

    @Modifying
    @PostMapping("/release/action/{id}")
    public void createReleaseAction(final int id, final int releaseid, final int id, boolean false, String name, int id)
    {
        releaseActionRepository.CreateReleaseAction(id, id, id, false, name, id )
        System.out.println("Release " + name + " created!");
}
}
