package com.cengage.WebAssignReleasePlanningTool.DueDateReport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReleaseWindow implements Comparable<ReleaseWindow>
{
	public static int MILLISECONDS_IN_DAY = 86400000;
	
	public static int TEST_WEIGHT = 5;
	public static int QUIZ_WEIGHT = 3;
	public static int OTHER_WEIGHT = 1;

	public Date startDate;
	public Date endDate;
	public double priorityScore;
	
	public List<Assignment> assignments; //list of assignments at this time, not in UML so discuss with team
	
	public ReleaseWindow(Date _startDate, Date _endDate)
	{
		setStartDate(_startDate);
		setEndDate(_endDate);
		
		assignments = new ArrayList<Assignment>();
	}
	
	public void setStartDate(Date _startDate)
	{
		startDate = _startDate;
	}
	
	public Date getStartDate()
	{
		return startDate;
	}
	
	public void setEndDate(Date _endDate)
	{
		endDate = _endDate;
	}
	
	public Date getEndDate()
	{
		return endDate;
	}
	
	

	@Override
	public int compareTo(ReleaseWindow other)
	{
		//first, check if either window contains a  > 1 hour assingment
		boolean hourLess = false;
		boolean hourLessOther = false;
		
		//get the score for each window as well
		int thisScore = 0;
		int otherScore = 0;
		
		//first check this
		for(Assignment a : assignments)
		{
			if(a.isTest())
			{
				thisScore += TEST_WEIGHT;
			}
			else if(a.isQuiz())
			{
				thisScore += QUIZ_WEIGHT;
			}
			else
			{
				thisScore += OTHER_WEIGHT;
			}
			
			if(a.getEndDate().getTime() - a.getStartDate().getTime() <= MILLISECONDS_IN_DAY)
			{
				hourLess = true;
				//System.out.println(a.getEndDate().getTime() - a.getStartDate().getTime());
			}
		}
		priorityScore = (double) thisScore;
		
		//then check other
		for(Assignment a : other.assignments)
		{
			if(a.isTest())
			{
				otherScore += TEST_WEIGHT;
			}
			else if(a.isQuiz())
			{
				otherScore += QUIZ_WEIGHT;
			}
			else
			{
				otherScore += OTHER_WEIGHT;
			}
			
			if(a.getEndDate().getTime() - a.getStartDate().getTime() <= MILLISECONDS_IN_DAY) //3600000 milliseconds = 1 hour
			{
				hourLessOther = true;
				//System.out.println("Other " + (a.getEndDate().getTime() - a.getEndDate().getTime()));
			}
		}
		
		other.priorityScore = (double) otherScore;
		
		//if one is less than an hour and not the other, then order accordingly
		if(hourLess && !hourLessOther)
			return 1;
		else if (!hourLess && hourLessOther)
			return -1;
		
		//otherwise, compare by priority sore
		int comp = new Double(priorityScore).compareTo(other.priorityScore);
		if(comp != 0)
			return comp;
		
		return 0;
	}
}
