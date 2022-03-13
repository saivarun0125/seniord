package com.cengage.WebAssignReleasePlanningTool.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.experimental.theories.internal.Assignments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cengage.WebAssignReleasePlanningTool.DueDateReport.Assignment;
import com.cengage.WebAssignReleasePlanningTool.DueDateReport.ReleaseWindow;
import com.cengage.WebAssignReleasePlanningTool.repositories.AssignmentRepository;

@CrossOrigin
@RestController
public class DueDateReportController {

    @Autowired
    AssignmentRepository assignmentRespository;

    public static int MILLISECONDS_IN_MINUTE = 60000;
    public static String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
    public static String DATE_FORMAT_JSON = "yyyy-MM-dd hh:mm a";
    
    @GetMapping("/duedatereport")
    public Map getDueDateReport(String startDate, String endDate, int duration)
    {
    	List<Assignment> assignments = assignmentRespository.findByDateRange(startDate, endDate);
    	
    	List<ReleaseWindow> windows = null;
    	try {
			windows = generateReleaseWindows(startDate, endDate, duration);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	int id = 0;
    	Map<String, Object> map = new LinkedHashMap<>();
    	
    	map.put("items", new ArrayList<LinkedHashMap<String, Object>>());
    	ArrayList<LinkedHashMap<String, Object>> items  = (ArrayList<LinkedHashMap<String, Object>>)map.get("items");
    	
    	DateFormat format = new SimpleDateFormat(DATE_FORMAT_JSON);
    	
    	//construct the correct correct json object for each release window
    	for(ReleaseWindow rw : windows)
    	{
    		LinkedHashMap<String, Object> currentMap = new LinkedHashMap<String, Object>();
    		
    		currentMap.put("id", id++);
    		currentMap.put("group", 1);
    		currentMap.put("className", "item-release-window");
    		currentMap.put("content", "Window #" + id);
    		currentMap.put("start", format.format(rw.startDate));
    		currentMap.put("end", format.format(rw.endDate));
    		currentMap.put("score", rw.priorityScore);
    		
    		items.add(currentMap);
    	}
    	
    	//construct the correct json object for each assignment
    	for(Assignment a : assignments)
    	{

			// Don't include assignments where days available > 1
			if(a.getDaysAvailable() <= 1) {
	    		LinkedHashMap<String, Object> currentMap = new LinkedHashMap<String, Object>();
	    		
	    		currentMap.put("id", id++);
	    		currentMap.put("group", 2);
	    		if(a.isTest())
	    			currentMap.put("className", "item-exam");
	    		currentMap.put("content", a.getName());
	    		currentMap.put("start", format.format(a.getStartDate()));
	    		currentMap.put("end", format.format(a.getEndDate()));
	    		
	    		((ArrayList<LinkedHashMap>) map.get("items")).add(currentMap);
			}
    	}
    	
    	map.put("rawData", new LinkedHashMap<String, Object>());
    	LinkedHashMap<String,Object> rawData = (LinkedHashMap<String, Object>) map.get("rawData");
    	
    	//initialize columns
    	rawData.put("columns", new ArrayList<LinkedHashMap<String, Object>>());
    	ArrayList<LinkedHashMap<String, Object>> columns = (ArrayList<LinkedHashMap<String, Object>>) rawData.get("columns");
    	
    	LinkedHashMap<String, Object> category = new LinkedHashMap<String, Object>();
    	category.put("field", "category");
    	category.put("header", "Category");
    	
    	LinkedHashMap<String, Object> startDateHeader = new LinkedHashMap<String, Object>();
    	startDateHeader.put("field", "startDate");
    	startDateHeader.put("header", "Start Date");
    	
    	LinkedHashMap<String, Object> endDateHeader = new LinkedHashMap<String, Object>();
    	endDateHeader.put("field", "endDate");
    	endDateHeader.put("header", "End Date");
    	
    	LinkedHashMap<String, Object> daysAvailable = new LinkedHashMap<String, Object>();
    	daysAvailable.put("field", "daysAvailable");
    	daysAvailable.put("header", "Days Available");
    	
    	LinkedHashMap<String, Object> rosterCount = new LinkedHashMap<String, Object>();
    	rosterCount.put("field", "rosterCount");
    	rosterCount.put("header", "Roster Count");    	
    	
    	columns.add(category);
    	columns.add(startDateHeader);
    	columns.add(endDateHeader);
    	columns.add(daysAvailable);
    	columns.add(rosterCount);
    	
    	//initialize rows
    	rawData.put("rows", new ArrayList<LinkedHashMap<String, Object>>());
    	ArrayList<LinkedHashMap<String, Object>> rows = (ArrayList<LinkedHashMap<String, Object>>)rawData.get("rows");
    	
    	for(Assignment a : assignments)
    	{
    		LinkedHashMap<String, Object> currentMap = new LinkedHashMap<String, Object>();
    		
    		currentMap.put("id", a.getAssignmentId());
    		currentMap.put("category", a.getCategory());
    		currentMap.put("startDate", format.format(a.getStartDate()));
    		currentMap.put("endDate", format.format(a.getEndDate()));
    		currentMap.put("daysAvailable", a.getDaysAvailable());
    		currentMap.put("rosterCount", a.getRosterCount());
    	
    		rows.add(currentMap);
    	}
    	
    	return map;
    }
    
    private List<ReleaseWindow> generateReleaseWindows(String startDate, String endDate, int duration) throws ParseException
    {
    	//Date startDate = new SimpleDateFormat("dd-MM-yyy-hh-mm").parse("02-03-2022-00-00");
    	//Date endDate = new SimpleDateFormat("dd-MM-yyy-hh-mm").parse("04-03-2022-00-00");
        //return assignmentRespository.findByDateRange(startDate, endDate);
    	System.out.println(startDate);
    	System.out.println(endDate);
    	
    	List<Assignment> assignments = assignmentRespository.findByDateRange(startDate, endDate);
    	
    	//generate release windows
    	List<ReleaseWindow> windows = new ArrayList<ReleaseWindow>();
    	
    	Date windowStart = new SimpleDateFormat(DATE_FORMAT).parse(startDate);
		windowStart.setMinutes(windowStart.getMinutes() + windowStart.getMinutes() % 15); //round up start to 15 minute mark
    	Date windowEnd = new Date(windowStart.getTime() + duration * MILLISECONDS_IN_MINUTE);
    	
		System.out.println(windowStart);
		System.out.println(windowEnd);
		
		Date end = new SimpleDateFormat(DATE_FORMAT).parse(endDate);
		
		System.out.println(windowEnd.compareTo(end));
		
		while(windowEnd.compareTo(end) <= 0)
		{
			ReleaseWindow rw = new ReleaseWindow(windowStart, windowEnd);
			
			windows.add(rw);
			
			windowStart = new Date(windowStart.getTime() + 15 * MILLISECONDS_IN_MINUTE);
			windowEnd = new Date(windowEnd.getTime() + 15 * MILLISECONDS_IN_MINUTE);
		}
    	
    	return orderReleaseWindowsByPriority(windows, assignments);
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
		//LinkedHashMap<ReleaseWindow, List<Assignment>> dict = new LinkedHashMap<ReleaseWindow, List<Assignment>>();
		
		//initialize assignment lists
		for(ReleaseWindow rw : inList)
		{			
			//add all assignments in the time window
			for(Assignment a : assignments)
			{
				if((a.getStartDate().compareTo(rw.startDate) <= 0 && a.getEndDate().compareTo(rw.startDate) >= 0)
					|| (a.getStartDate().compareTo(rw.endDate) <= 0 && a.getEndDate().compareTo(rw.endDate) >= 0))
				{
					//System.out.println(rw + " " + a);
					rw.assignments.add(a);
				}
			}
		}
		
		//sort list
		Collections.sort(inList);
		
		//note: inefficient sort and does not take all factors into account, will need to be changed later
//		while(outList.size() != inList.size())
//		{
//			ReleaseWindow lowest = null;
//			for(ReleaseWindow rw : inList)
//			{
//				if(lowest == null || rw.compareTo(lowest) >= 0)
//				{
//					lowest = rw;
//				}
//			}
//			
//			//System.out.print(lowest.startDate + " " + dict.get(lowest).size() + " ");
//			for(Assignment a : lowest.assignments)
//			{
//				//System.out.print(a.getCategory() + " ");
//			}
//			//System.out.println();
//			outList.add(lowest);
//		}
		
		return inList;
	}
}
