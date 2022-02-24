package com.cengage.WebAssignReleasePlanningTool.DueDateReport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.cfg.NotYetImplementedException;

public class DueDateReport {

	private List<Assignment> assignments;
	
	public DueDateReport(Date day)
	{
		assignments = new ArrayList<Assignment>();
		retrieveAssignments(day);
	}
	
	//used for testing
	public DueDateReport(List<Assignment> _assignments)
	{
		assignments = _assignments;
	}
	
	private void retrieveAssignments(Date day)
	{
		//pull from database all assignments for the given day and load the into the assignments list
		throw new NotYetImplementedException();
	}
	
	public ReleaseWindow generateBestReleaseWindow(Date startDate, Date EndDate)
	{
		//get best release window in the given date range
		throw new NotYetImplementedException();
	}
	
	public List<ReleaseWindow> generateReleaseWindows(Date startDate, Date endDate)
	{
		//generate all release windows
		throw new NotYetImplementedException();
	}
}
