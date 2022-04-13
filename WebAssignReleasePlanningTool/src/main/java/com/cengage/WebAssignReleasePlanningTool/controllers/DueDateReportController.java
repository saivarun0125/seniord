package com.cengage.WebAssignReleasePlanningTool.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
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
    public static String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static String DATE_FORMAT_JSON = "yyyy-MM-dd hh:mm a";
    
    public static int PERCENT_THRESH = 10;
    
	public static int JOIN_SCORE_PERCENT_RANGE = 15;
    
    @GetMapping("/duedatereport")
    public Map getDueDateReport(String startDate, String endDate, int duration)
    {
    	List<Assignment> assignments = assignmentRespository.findByDateRange(startDate, endDate);
    	
    	List<ReleaseWindow> windows = null;
    	try
    	{
			windows = generateReleaseWindows(startDate, endDate, duration);
		} 
    	catch (ParseException e)
    	{
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
			if(a.getDaysAvailable() <= 1  && (a.isTest() || a.isQuiz())) {
	    		LinkedHashMap<String, Object> currentMap = new LinkedHashMap<String, Object>();
	    		
	    		currentMap.put("id", id++);
	    		currentMap.put("group", 2);
	    		if(a.isTest())
	    			currentMap.put("className", "item-exam");
	    		else
	    			currentMap.put("className", "item-assignment");
	    		currentMap.put("content", a.getName());
	    		currentMap.put("category", a.getCategory());
	    		currentMap.put("daysAvailable", a.getDaysAvailable());
	    		currentMap.put("rosterCount", a.getRosterCount());
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
  	
    	List<Assignment> assignments = assignmentRespository.findByDateRange(startDate, endDate);
    	
    	//generate release windows
    	PriorityQueue<ReleaseWindow> windows = new PriorityQueue<ReleaseWindow>();
    	
    	Date windowStart = new SimpleDateFormat(DATE_FORMAT).parse(startDate);
		windowStart.setMinutes(windowStart.getMinutes() + windowStart.getMinutes() % 15); //round up start to 15 minute mark
    	Date windowEnd = new Date(windowStart.getTime() + duration * MILLISECONDS_IN_MINUTE);
		
		Date end = new SimpleDateFormat(DATE_FORMAT).parse(endDate);
		
		while(windowEnd.compareTo(end) <= 0)
		{
			ReleaseWindow rw = new ReleaseWindow(windowStart, windowEnd);
			addAssignmentsForWindow(rw, assignments);
			
			windows.add(rw);
			
			windowStart = new Date(windowStart.getTime() + 15 * MILLISECONDS_IN_MINUTE);
			windowEnd = new Date(windowEnd.getTime() + 15 * MILLISECONDS_IN_MINUTE);
		}
    	

		//add the top % or release windows to a list
    	int topCount = windows.size() / (100 / PERCENT_THRESH);
    	
    	List<ReleaseWindow> topList = new ArrayList<ReleaseWindow>();
    	
    	for(int i = 0; i < topCount; i++)
    	{
    		topList.add(windows.poll());
    	}
    	
    	return joinReleaseWindows(topList, windows);
    }
	
	private void addAssignmentsForWindow(ReleaseWindow rw, List<Assignment> assignments)
	{
		for(Assignment a : assignments)
		{
			if((a.getStartDate().compareTo(rw.startDate) <= 0 && a.getEndDate().compareTo(rw.startDate) >= 0)
					|| (a.getStartDate().compareTo(rw.endDate) <= 0 && a.getEndDate().compareTo(rw.endDate) >= 0))
				{
					rw.assignments.add(a);
				}
		}		
	}
	
	private List<ReleaseWindow> joinReleaseWindows(List<ReleaseWindow> inList, Queue<ReleaseWindow> fullList)
	{		
		
		List<ReleaseWindow> newList = new ArrayList<ReleaseWindow>();
		
		//first join similar inlist windows
		while(inList.size() > 0)
		{
			ReleaseWindow curr = inList.get(0);
			
			double joinUpperBound = curr.priorityScore + curr.priorityScore * (100 / JOIN_SCORE_PERCENT_RANGE);
			double joinLowerBound = curr.priorityScore - curr.priorityScore * (100 / JOIN_SCORE_PERCENT_RANGE);
			
			//check if there's already a rw we can join
			boolean joined = false;
			for(ReleaseWindow rw : newList)
			{				
				if(rw.priorityScore > joinUpperBound || rw.priorityScore < joinLowerBound)
				{
					continue;
				}
				
    			//check left endpoint
    			if(curr.getStartDate().compareTo(rw.getStartDate()) >= 0 
    					&& curr.getStartDate().compareTo(rw.getEndDate()) <= 0
    					&& curr.getEndDate().compareTo(rw.getEndDate()) >= 0)
    			{
    				rw.setEndDate(curr.getEndDate());
    				joined = true;
    			}
    			//check right endpoint
    			else if (curr.getEndDate().compareTo(rw.getStartDate()) >= 0
    					&& curr.getStartDate().compareTo(rw.getEndDate()) <= 0
    					&& curr.getStartDate().compareTo(rw.getStartDate()) <= 0)
    			{
    				rw.setStartDate(curr.getStartDate());
    				joined = true;
    			}
			}
	
			if(!joined)
			{
				newList.add(curr);
			}
			inList.remove(0);
		}
		
		inList = newList;
		
		//then extend the windows with any "close enough" windows from the full list
		for(ReleaseWindow rw : inList)
		{
			double joinUpperBound = rw.priorityScore + rw.priorityScore * (100 / JOIN_SCORE_PERCENT_RANGE);
			double joinLowerBound = rw.priorityScore - rw.priorityScore * (100 / JOIN_SCORE_PERCENT_RANGE);
    		
			for(ReleaseWindow o : fullList)
    		{
    			if(o.priorityScore > joinUpperBound || o.priorityScore < joinLowerBound)
    			{
    				continue;
    			}
    			
    			//check left endpoint
    			if(o.getStartDate().compareTo(rw.getStartDate()) >= 0 
    					&& o.getStartDate().compareTo(rw.getEndDate()) <= 0
    					&& o.getEndDate().compareTo(rw.getEndDate()) >= 0)
    			{
    				rw.setEndDate(o.getEndDate());
    			}
    			//check right endpoint
    			else if (o.getEndDate().compareTo(rw.getStartDate()) >= 0
    					&& o.getStartDate().compareTo(rw.getEndDate()) <= 0
    					&& o.getStartDate().compareTo(rw.getStartDate()) <= 0)
    			{
    				rw.setStartDate(o.getStartDate());
    			}
    		}
		}
		
		//remove subsets
		for(int i = 0; i < newList.size(); i++)
		{
			ReleaseWindow rw = newList.get(i);
			//delete o if it's a subset
			for(int j = 0; j < newList.size(); j++)
			{
				if(i == j)
					continue;
				
				ReleaseWindow o = newList.get(j);
				
				if(o.getStartDate().compareTo(rw.getStartDate()) >= 0
						&& o.getEndDate().compareTo(rw.getEndDate()) <= 0)
				{
					newList.remove(j);
					j--;
				}
			}
		}
		
		return newList;
	}
}