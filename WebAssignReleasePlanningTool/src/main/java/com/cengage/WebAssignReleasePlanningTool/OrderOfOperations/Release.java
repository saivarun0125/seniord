package com.cengage.WebAssignReleasePlanningTool.OrderOfOperations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "releases")
public class Release {

	@Id
	private int id;
    private String                    name;
    
    @Transient
    private final List<ReleaseAction> releaseActions;
    @Transient
    private final List<ReleaseAction> rollbackActions;

    public Release()
    {
    	releaseActions = new ArrayList<ReleaseAction>();
        rollbackActions = new ArrayList<ReleaseAction>();
    }
    
    Release ( final String _name ) {
        setName( _name );
        releaseActions = new ArrayList<ReleaseAction>();
        rollbackActions = new ArrayList<ReleaseAction>();
    }

    public int getId()
    {
    	return id;
    }
    
    public void setId(int _id)
    {
    	id = _id;
    }
    
    public String getName () {
        return name;
    }

    public void setName ( final String name ) {
        this.name = name;
    }

    public List<ReleaseAction> getReleaseActions () {
        return releaseActions;
    }

    public List<ReleaseAction> getRollbackActions () {
        return rollbackActions;
    }

    public int getDuration () {
        int dur = 0;
    	for(ReleaseAction ra : releaseActions)
    	{
    		dur += ra.getDuration();
    	}
    	
    	return dur;
    }
}
