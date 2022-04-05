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
    
	public static int JOIN_SCORE_PERCENT_RANGE = 5;
    
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
    	System.out.println(startDate);
    	System.out.println(endDate);
    	
    	List<Assignment> assignments = assignmentRespository.findByDateRange(startDate, endDate);
    	
    	//generate release windows
    	List<ReleaseWindow> windows = new ArrayList<ReleaseWindow>();
    	
    	Date windowStart = new SimpleDateFormat(DATE_FORMAT).parse(startDate);
		windowStart.setMinutes(windowStart.getMinutes() + windowStart.getMinutes() % 15); //round up start to 15 minute mark
    	Date windowEnd = new Date(windowStart.getTime() + duration * MILLISECONDS_IN_MINUTE);
		
		Date end = new SimpleDateFormat(DATE_FORMAT).parse(endDate);
		
		while(windowEnd.compareTo(end) <= 0)
		{
			ReleaseWindow rw = new ReleaseWindow(windowStart, windowEnd);
			
			windows.add(rw);
			
			windowStart = new Date(windowStart.getTime() + 15 * MILLISECONDS_IN_MINUTE);
			windowEnd = new Date(windowEnd.getTime() + 15 * MILLISECONDS_IN_MINUTE);
		}
    	
    	windows = orderReleaseWindowsByPriority(windows, assignments);
    	
    	windows = windows.subList(0, windows.size() / (100 / PERCENT_THRESH));
    	
    	return joinReleaseWindows(windows);
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
					rw.assignments.add(a);
				}
			}
		}
		
		//sort list
		Collections.sort(inList);
		
		return inList;
	}
	
	private List<ReleaseWindow> joinReleaseWindows(List<ReleaseWindow> inList)
	{
		List<ReleaseWindow> outList = new ArrayList<ReleaseWindow>();
		
		while(inList.size() > 0)
    	{
    		ReleaseWindow curr = inList.get(0);
    		boolean joined = false;
    		
    		double joinUpperBound = curr.priorityScore + curr.priorityScore * JOIN_SCORE_PERCENT_RANGE;
    		double joinLowerBound = curr.priorityScore - curr.priorityScore * JOIN_SCORE_PERCENT_RANGE;

    		//check if we can join to an already existing window
    		for(ReleaseWindow rw : outList)
    		{
    			if(rw.priorityScore > joinUpperBound || rw.priorityScore < joinLowerBound)
    			{
    				continue;
    			}
    			if(curr.endDate.compareTo(rw.startDate) >= 0 && curr.endDate.compareTo(rw.endDate) <= 0 && curr.startDate.compareTo(rw.startDate) <= 0)
    			{
    				rw.setStartDate(curr.startDate);
    				rw.assignments = Stream.concat(curr.assignments.stream(), rw.assignments.stream()).collect(Collectors.toList());;
    				joined = true;
    				break;
    			}
    			else if (curr.startDate.compareTo(rw.startDate) >= 0 && curr.startDate.compareTo(rw.endDate) <= 0 && curr.endDate.compareTo(rw.endDate) >= 0)
    			{
    				rw.setEndDate(curr.endDate);
    				rw.assignments = Stream.concat(curr.assignments.stream(), rw.assignments.stream()).collect(Collectors.toList());
    				joined = true;
    				break;
    			}
    			else if (curr.startDate.compareTo(rw.startDate) >= 0 && curr.endDate.compareTo(rw.endDate) <= 0)
    			{
    				rw.assignments = Stream.concat(curr.assignments.stream(), rw.assignments.stream()).collect(Collectors.toList());
    				joined = true;
    				break;
    			}
    		}
    		
    		//otherwise make a new one
    		if(!joined)
    		{
    			outList.add(curr);
    		}
    		
    		inList.remove(0);
    	}
		
		//clean list
		for(int i = 0; i < outList.size(); i++)
		{
    		double joinUpperBound = outList.get(i).priorityScore + outList.get(i).priorityScore * JOIN_SCORE_PERCENT_RANGE;
    		double joinLowerBound = outList.get(i).priorityScore - outList.get(i).priorityScore * JOIN_SCORE_PERCENT_RANGE;
    		
			for(int j = 0; j < outList.size(); j++)
			{
				if(i == j || outList.get(j).priorityScore > joinUpperBound ||outList.get(j).priorityScore < joinLowerBound)
				{
					continue;
				}
				
				//range is a subset of other range
				if(outList.get(i).getStartDate().compareTo(outList.get(j).getStartDate()) >= 0
					&& outList.get(i).getEndDate().compareTo(outList.get(j).getEndDate()) <= 0)
				{
					outList.remove(i);
					if(i > 0)
						i--;
					if(j > 0)
						j--;
				}
				
				else if(outList.get(i).getStartDate().compareTo(outList.get(j).getStartDate()) >= 0
						&& outList.get(i).getStartDate().compareTo(outList.get(j).getEndDate()) <= 0)
				{
					if(outList.get(i).getEndDate().compareTo(outList.get(j).getEndDate()) >= 0)
					{
						outList.get(j).setEndDate(outList.get(i).getEndDate());
						outList.get(j).assignments = Stream.concat(outList.get(j).assignments.stream(), outList.get(i).assignments.stream()).collect(Collectors.toList());
					}
					
					outList.remove(i);
					if(i > 0)
						i--;
					if(j > 0)
						j--;
				}
			}
		}
		
		Collections.sort(outList);
		return outList;
	}
}