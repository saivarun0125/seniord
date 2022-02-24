package com.cengage.WebAssignReleasePlanningTool.DueDateReport;

import java.util.Date;

public class ReleaseWindow {

	public Date startDate;
	public Date endDate;
	public double priorityScore;
	
	public ReleaseWindow(Date _startDate, Date _endDate)
	{
		setStartDate(_startDate);
		setEndDate(_endDate);
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
}
