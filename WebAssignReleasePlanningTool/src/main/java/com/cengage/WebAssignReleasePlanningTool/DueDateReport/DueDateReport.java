package com.cengage.WebAssignReleasePlanningTool.DueDateReport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
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
    	assignments = new ArrayList<Assignment>();
    }
    
	public DueDateReport(Date day)
	{
		assignments = new ArrayList<Assignment>();
		//retrieveAssignments(day);
		generateAssignmentsTest();
	}
	
	//used for testing
	public DueDateReport(List<Assignment> _assignments)
	{
		assignments = _assignments;
	}
	
	private void retrieveAssignments(Date day)
	{
		//pull from database all assignments for the given day and load the into the assignments list
		//System.out.print(repository);
		List<Assignment> allAssignments = repository.findAll();
		 
		 for(Assignment a : allAssignments)
		 {
			 if(a.getStartDate().compareTo(day) >= 0 && a.getEndDate().compareTo(day) <= 0)
			 {
				 assignments.add(a);
			 }
		 }
		 
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
		List<ReleaseWindow> windows = new ArrayList<ReleaseWindow>();
		
		//generate all release windows

		//for now create a list of 1 hour windows in 15 minute increments and put them in a list
		Date windowStart = startDate;
		Date windowEnd = new Date(windowStart.getTime() +  3600000); //3600000 = 1 hour make this a static const later
		
		//round up to nearest quarter hour
		windowStart.setMinutes(windowStart.getMinutes() + windowStart.getMinutes() % 15);
		windowEnd.setMinutes(windowEnd.getMinutes() + windowEnd.getMinutes() % 15);
		
		while(windowEnd.compareTo(endDate) <= 0)
		{
			ReleaseWindow rw = new ReleaseWindow(windowStart, windowEnd);
			
			windows.add(rw);
			
			windowStart = new Date(windowStart.getTime() + 900000); //900000 milliseconds = 15 minutes, make this a static const later
			windowEnd = new Date(windowEnd.getTime() + 900000);
			
			System.out.println("f" + windowEnd);
		}
		
		windows = orderReleaseWindowsByPriority(windows);	
		
		return windows; //just return a list of the release windows now
	}
	
	private List<ReleaseWindow> orderReleaseWindowsByPriority(List<ReleaseWindow> inList)
	{
		List<ReleaseWindow> outList = new ArrayList<ReleaseWindow>();
		
		HashMap<ReleaseWindow, List<Assignment>> dict = new HashMap<ReleaseWindow, List<Assignment>>();
		
		//initialize dictionary
		for(ReleaseWindow rw : inList)
		{
			//add a new key for releaseWindow
			dict.put(rw, new ArrayList<Assignment>());
			
			//add all assignments in the time window
			for(Assignment a : assignments)
			{
				if((a.getStartDate().compareTo(rw.startDate) <= 0 && a.getEndDate().compareTo(rw.startDate) >= 0)
					|| (a.getStartDate().compareTo(rw.endDate) <= 0 && a.getEndDate().compareTo(rw.endDate) >= 0))
				{
					dict.get(rw).add(a);
				}
			}
		}
		
		//sort list
		//note: inefficient sort and does not take all factors into acount, will need to be changed later
		while(outList.size() != inList.size())
		{
			ReleaseWindow lowest = null;
			for(ReleaseWindow key : dict.keySet())
			{
				if(!outList.contains(key) && (lowest == null || dict.get(key).size() < dict.get(lowest).size()))
				{
					lowest = key;
				}
			}
			
			System.out.print(lowest.startDate + " " + dict.get(lowest).size() + " ");
			for(Assignment a : dict.get(lowest))
			{
				System.out.print(a.getCategory() + " ");
			}
			System.out.println();
			outList.add(lowest);
		}
		
		return outList;
	}
	
	//classes below are temporary test classes, they should be deleted once connection to repository is working correctly
	public void generateAssignmentsTest()
	{
		//generate a set of assignments for testing
		assignments.add(new Assignment("homework 1", new Date(2022, 2, 1, 1, 0), new Date(2022, 2, 1, 4, 0), 1, 25));
		assignments.add(new Assignment("test 1", new Date(2022, 2, 1, 4, 0), new Date(2022, 2, 1, 5, 0), 1, 25));
		assignments.add(new Assignment("homework 2", new Date(2022, 2, 1, 5, 0), new Date(2022, 2, 1, 6, 0), 1, 25));
		assignments.add(new Assignment("homework 3", new Date(2022, 2, 2, 5, 0), new Date(2022, 2, 2, 6, 0), 1, 25));
		assignments.add(new Assignment("homework 4", new Date(2022, 2, 1, 5, 45), new Date(2022, 2, 1, 6, 0), 1, 25));
	}
}
