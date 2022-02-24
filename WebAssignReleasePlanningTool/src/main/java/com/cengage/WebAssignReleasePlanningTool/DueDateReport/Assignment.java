package com.cengage.WebAssignReleasePlanningTool.DueDateReport;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "assignments")
public class Assignment {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String category;
	private Date startDate;
	private Date endDate;
	private int daysAvailable;
	private int rosterCount;

    public Assignment() {  
    	
    }
	
	public Assignment(String _category, Date _startDate, Date _endDate, int _daysAvailable, int _rosterCount)
	{
		setCategory(_category);
		setStartDate(_startDate);
		setEndDate(_endDate);
		setDaysAvailable(_daysAvailable);
		setRosterCount(_rosterCount);
	}
	
	public void setCategory(String _category)
	{
		category = _category;
	}
	
	public String getCategory()
	{
		return category;
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
	
	public void setDaysAvailable(int _daysAvailable)
	{
		daysAvailable = _daysAvailable;
	}
	
	public int getDaysAvailable()
	{
		return daysAvailable;
	}
	
	public void setRosterCount(int _rosterCount)
	{
		rosterCount = _rosterCount;
	}
	
	public int getRosterCount()
	{
		return rosterCount;
	}

    @Override
    public String toString() {
        return "Assignment{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", daysAvailable='" + daysAvailable + '\'' +
                ", rosterCount='" + rosterCount + '\'' +
                '}';
    }
}
