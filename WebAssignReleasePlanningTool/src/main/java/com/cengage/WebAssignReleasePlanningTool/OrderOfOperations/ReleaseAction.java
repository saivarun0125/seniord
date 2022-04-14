package com.cengage.WebAssignReleasePlanningTool.OrderOfOperations;

import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "releaseaction")
public class ReleaseAction {

	@Id
	private int id;
    private String        name;
    private String        notes;
    private Time          duration;
    @Transient
    private ReleaseAction rollback;
    @Transient
    private boolean       isSelected;

    public ReleaseAction () {

    }

    public ReleaseAction ( final String name, final Time duration ) {
    	setName(name);
    	setDuration(duration);
    }

    public String getName () {
        return name;
    }

    public void setName ( final String name ) {
        this.name = name;
    }

    public String getNotes () {
        return notes;
    }

    public void setNotes ( final String notes ) {
        this.notes = notes;
    }

    public Time getDuration () {
        return duration;
    }

    public void setDuration ( final Time duration ) {
        this.duration = duration;
    }

    public ReleaseAction getRollback () {
        return rollback;
    }

    public void setRollback ( final ReleaseAction rollback ) {
        this.rollback = rollback;
    }

    public boolean isSelected () {
        return isSelected;
    }

    public void setSelected ( final boolean isSelected ) {
        this.isSelected = isSelected;
    }

}
