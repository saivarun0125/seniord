package com.cengage.WebAssignReleasePlanningTool.DueDateReport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
		//throw new NotYetImplementedException();
		
		//placeholder implementation below, this is not how the method will actually work I just used this for testing
		List<Assignment> assignmentsInRange = new ArrayList<Assignment>();
		
		for(Assignment a : assignments)
		{
			if(a.getStartDate().compareTo(startDate) <= 0 && a.getEndDate().compareTo(EndDate) >= 0)
			{
				assignmentsInRange.add(a);
			}
			
		}
		
		//sort assignments from best to release during to worst
		Collections.sort(assignmentsInRange);
		
		//placeholder: for now we get the best assignment and set is as a window
		Assignment best = assignmentsInRange.get(0);
		
		return new ReleaseWindow(best.getStartDate(), best.getEndDate());
	}
	
	public List<ReleaseWindow> generateReleaseWindows(Date startDate, Date endDate)
	{
		//generate all release windows
		throw new NotYetImplementedException();
	}
}
