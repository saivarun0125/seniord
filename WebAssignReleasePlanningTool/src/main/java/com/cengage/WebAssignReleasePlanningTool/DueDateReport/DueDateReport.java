package com.cengage.WebAssignReleasePlanningTool.DueDateReport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cengage.WebAssignReleasePlanningTool.repositories.AssignmentRepository;

@Component
public class DueDateReport {

	public List<Assignment> assignments;
	
    @Autowired
    AssignmentRepository repository;
	
    public DueDateReport()
    {
    	
    }
    
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
		/*
		 * System.out.print(repository); List<Assignment> allAssignments =
		 * repository.findAll();
		 * 
		 * for(Assignment a : allAssignments) { if(a.getStartDate().compareTo(day) >= 0
		 * && a.getEndDate().compareTo(day) <= 0) { assignments.add(a); } }
		 */
	}
	
	public ReleaseWindow generateBestReleaseWindow(Date startDate, Date EndDate)
	{
		//get best release window in the given date range
		
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
