package com.cengage.WebAssignReleasePlanningTool.DueDateReport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReleaseWindow implements Comparable<ReleaseWindow>
{
	public static int MILLISECONDS_IN_DAY = 86400000;
	
	public static int DAY_OR_LESS_MODIFIER = 5;
	public static int TEST_WEIGHT = 25;
	public static int QUIZ_WEIGHT = 10;
	public static int OTHER_WEIGHT = 1;
	
	public Date startDate;
	public Date endDate;
	public double priorityScore;
	
	public List<Assignment> assignments; //list of assignments at this time
	
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
		if(priorityScore <= 0)
			calculateScore();
		if(other.priorityScore <= 0)
			other.calculateScore();
		
		//System.out.println(priorityScore);
		
		//otherwise, compare by priority sore
		if(priorityScore > other.priorityScore)
			return 1;
		else if (other.priorityScore > priorityScore)
			return -1;
		
		return 0;
	}
	
	public void calculateScore()
	{
		//System.out.println(assignments.size());
		priorityScore = 0;
		for(Assignment a : assignments)
		{
			int scoreAdd = OTHER_WEIGHT;
			if(a.isTest())
			{
				scoreAdd = TEST_WEIGHT;
			}
			else if(a.isQuiz())
			{
				scoreAdd = QUIZ_WEIGHT;
			}
			
			if(a.getEndDate().getTime() - a.getStartDate().getTime() <= MILLISECONDS_IN_DAY)
			{
				scoreAdd *= DAY_OR_LESS_MODIFIER;
			}
			
			// calculate proportion of the assignment window within the release window
			// does not apply to assignments shorter than the release window
			
			if(endDate.getTime() - endDate.getTime() < a.getEndDate().getTime() - a.getStartDate().getTime())
			{
				double intersect = a.getEndDate().getTime() - a.getStartDate().getTime();
				if(a.getStartDate().compareTo(startDate) > 0)
				{
					intersect -= a.getStartDate().getTime() - startDate.getTime();
				}
				if(a.getEndDate().compareTo(endDate) < 0)
				{
					intersect -= endDate.getTime() - a.getEndDate().getTime();
				}
				
				double ratio = 1 - intersect / (a.getEndDate().getTime() - a.getStartDate().getTime());
				scoreAdd *= ratio;
			}
			
			scoreAdd *= a.getRosterCount();
			
			priorityScore += scoreAdd;
			//System.out.println(priorityScore);
		}
	}
}
