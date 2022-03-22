package com.cengage.WebAssignReleasePlanningTool.DueDateReport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReleaseWindow implements Comparable<ReleaseWindow>
{
	public static int MILLISECONDS_IN_DAY = 86400000;
	
	public static int DAY_OR_LESS_MODIFIER = 10;
	public static int TEST_WEIGHT = 9;
	public static int QUIZ_WEIGHT = 5;
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
		double thisScore = 0;
		double otherScore = 0;
		
		//first check this

//		priorityScore = (double) thisScore;
//		
//		//then check other
//		for(Assignment a : other.assignments)
//		{
//			int scoreAdd = OTHER_WEIGHT;
//			if(a.isTest())
//			{
//				scoreAdd += TEST_WEIGHT;
//			}
//			else if(a.isQuiz())
//			{
//				scoreAdd += QUIZ_WEIGHT;
//			}
//
//			
//			if(a.getEndDate().getTime() - a.getStartDate().getTime() <= MILLISECONDS_IN_DAY)
//			{
//				hourLessOther = true;
//				scoreAdd *= DAY_OR_LESS_MODIFIER;
//				//System.out.println("Other " + (a.getEndDate().getTime() - a.getEndDate().getTime()));
//			}
//			
//			otherScore += scoreAdd;
//		}
//		
		if(priorityScore == 0)
			calculateScore();
		if(other.priorityScore == 0)
			other.calculateScore();
		
		thisScore = priorityScore;
		otherScore = other.priorityScore;
		
//		other.priorityScore = (double) otherScore;
		
		//if one is less than an hour and not the other, then order accordingly
//		if(hourLess && !hourLessOther)
//			return 1;
//		else if (!hourLess && hourLessOther)
//			return -1;
		
		//otherwise, compare by priority sore
		if(thisScore > otherScore)
			return 1;
		else if (otherScore > thisScore)
			return -1;
		
		return 0;
	}
	
	public void calculateScore()
	{
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
			
			priorityScore += scoreAdd;
		}
	}
}
