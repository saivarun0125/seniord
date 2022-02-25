package com.cengage.WebAssignReleasePlanningTool.DueDateReport;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "assignments")
public class Assignment implements Comparable<Assignment> {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
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
	
	public Integer getAssignmentId()
	{
		return id;
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

	@Override
	public int compareTo(Assignment other)
	{
		//placeholder implementation
		
		//first filter by category
		if(category != null) {
			//first check for exam
			if(category.toLowerCase().contains("exam") && !other.category.toLowerCase().contains("exam"))
			{
				return -1;
			}
			else if(!category.toLowerCase().contains("exam") && other.category.toLowerCase().contains("exam"))
			{
				return 1;
			}
			//then quiz
			else if(category.toLowerCase().contains("quiz") && !other.category.toLowerCase().contains("quiz"))
			{
				return -1;
			}
			else if(!category.toLowerCase().contains("quiz") && other.category.toLowerCase().contains("quiz"))
			{
				return 1;
			}
		}
		
		//then check by days available
		int dayCompare = new Integer(daysAvailable).compareTo(other.daysAvailable);
		
		if(dayCompare != 0)
			return dayCompare;
		
		//then check roster count
		return new Integer(rosterCount).compareTo(other.rosterCount);
	}
}
