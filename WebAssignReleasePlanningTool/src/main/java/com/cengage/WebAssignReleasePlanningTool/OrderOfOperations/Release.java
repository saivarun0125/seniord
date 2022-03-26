package com.cengage.WebAssignReleasePlanningTool.OrderOfOperations;

import java.util.ArrayList;
import java.util.List;

public class Release {

    private String                    name;
    private final List<ReleaseAction> releaseActions;
    private final List<ReleaseAction> rollbackActions;

    Release ( final String _name ) {
        setName( _name );
        releaseActions = new ArrayList<ReleaseAction>();
        rollbackActions = new ArrayList<ReleaseAction>();
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
        return 0;
    }
}
