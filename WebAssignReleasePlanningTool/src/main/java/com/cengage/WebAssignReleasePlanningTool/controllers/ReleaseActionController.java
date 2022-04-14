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

import com.cengage.WebAssignReleasePlanningTool.OrderOfOperations.ReleaseAction;
import com.cengage.WebAssignReleasePlanningTool.repositories.ReleaseActionRepository;

@CrossOrigin
@RestController
public class ReleaseActionController {

    @Autowired
    ReleaseActionRepository releaseActionRepository;

    @GetMapping ( "/release/action" )
    public List<ReleaseAction> getReleaseActions () {

        return releaseActionRepository.findAll();
    }

    @GetMapping ( "/release/action/{id}" )
    public List<ReleaseAction> getReleaseAction ( @PathVariable final int id ) {
        return releaseActionRepository.findById( id );
    }

    @Modifying
    @PutMapping ( "/release/action/{id}" )
    public void updateReleaseAction ( @PathVariable final int id, @RequestParam final int releaseId, @RequestParam final String name,
            @RequestParam final int rollback, @RequestParam final boolean isSelected, @RequestParam final String notes,
            @RequestParam final int duration ) {
        releaseActionRepository.updateById( id, releaseId, rollback, isSelected, notes, duration, name );
        System.out.println( "ReleaseAction " + id + " updated!" );
    }

    @Modifying
    @PostMapping ( "/release/action/{id}" )
    public void createReleaseAction ( @PathVariable final int id, @RequestParam final int releaseId, @RequestParam final String name,
            @RequestParam final int rollback, @RequestParam final boolean isSelected, @RequestParam final String notes,
            @RequestParam final int duration ) {
        releaseActionRepository.CreateReleaseAction( id, releaseId, rollback, isSelected, notes, duration, name );
        System.out.println( "ReleaseAction " + id + " created!" );
    }
}
