package com.cengage.WebAssignReleasePlanningTool.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cengage.WebAssignReleasePlanningTool.DueDateReport.Assignment;
import com.cengage.WebAssignReleasePlanningTool.DueDateReport.ReleaseWindow;
import com.cengage.WebAssignReleasePlanningTool.repositories.AssignmentRepository;

@RestController
public class DueDateReportController {

    @Autowired
    AssignmentRepository assignmentRespository;

    @GetMapping("/due-date")
    public List<Assignment> getDueDateReport() throws ParseException
    {
    	Date startDate = new SimpleDateFormat("dd-MM-yyy-hh-mm").parse("02-03-2022-00-00");
    	Date endDate = new SimpleDateFormat("dd-MM-yyy-hh-mm").parse("04-03-2022-00-00");
        //return assignmentRespository.findByDateRange(startDate, endDate);
    	return assignmentRespository.findByDateRange("02-03-2022-00-00", "04-03-2022-00-00");
    }
    
    @GetMapping("due-date-test")
    public List<ReleaseWindow> getDueDateTest(String startDateString, String endDateString) throws ParseException
    {
    	Date startDate = null;
    	Date endDate = null;
    	
        startDate = new SimpleDateFormat("dd-MM-yyy").parse(startDateString);
        	endDate = new SimpleDateFormat("dd-MM-yyyy").parse(endDateString);
    			
    	//System.out.println(startDate);
    	//System.out.println(endDate);
    	
    	List<Assignment> assignments = new ArrayList<Assignment>();
    	//generate a set of assignments for testing
    	assignments.add(new Assignment("homework 1", 
    			new SimpleDateFormat("dd-MM-yyy-hh-mm").parse("01-03-2022-01-00"),
    			new SimpleDateFormat("dd-MM-yyy-hh-mm").parse("01-03-2022-04-00"), 1, 25));
    	assignments.add(new Assignment("test 1",
    			new SimpleDateFormat("dd-MM-yyy-hh-mm").parse("01-03-2022-04-00"),
    			new SimpleDateFormat("dd-MM-yyy-hh-mm").parse("01-03-2022-05-00"), 1, 25));
    	assignments.add(new Assignment("homework 2",
    			new SimpleDateFormat("dd-MM-yyy-hh-mm").parse("01-03-2022-05-00"),
    			new SimpleDateFormat("dd-MM-yyy-hh-mm").parse("01-03-2022-06-00"), 1, 25));
    	assignments.add(new Assignment("homework 3",
    			new SimpleDateFormat("dd-MM-yyy-hh-mm").parse("02-03-2022-05-00"),
    			new SimpleDateFormat("dd-MM-yyy-hh-mm").parse("02-03-2022-06-00"), 1, 25));
    	assignments.add(new Assignment("homework 4",
    			new SimpleDateFormat("dd-MM-yyy-hh-mm").parse("01-03-2022-05-45"),
    			new SimpleDateFormat("dd-MM-yyy-hh-mm").parse("01-03-2022-06-00"), 1, 25));
    	
    	System.out.println(assignments.get(0).getStartDate());
    	
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
			
			//System.out.println("f" + windowEnd);
		}
		
		windows = orderReleaseWindowsByPriority(windows, assignments);	
		
		return windows; //just return a list of the release windows now
    }
    
    /**
	 * Takes in an unordered list of release windows and puts them in order
	 * @param inList an unordered list of release windows to be ordered
	 * @return an ordered list of release windows
	 */
	private List<ReleaseWindow> orderReleaseWindowsByPriority(List<ReleaseWindow> inList, List<Assignment> assignments)
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
					System.out.println(rw + " " + a);
					dict.get(rw).add(a);
					rw.assignments.add(a);
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
			
			//System.out.print(lowest.startDate + " " + dict.get(lowest).size() + " ");
			for(Assignment a : dict.get(lowest))
			{
				//System.out.print(a.getCategory() + " ");
			}
			//System.out.println();
			outList.add(lowest);
		}
		
		return outList;
	}
}
