package com.cengage.WebAssignReleasePlanningTool.DueDateReport;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.cfg.NotYetImplementedException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cengage.WebAssignReleasePlanningTool.controllers.AssignmentController;

@SpringBootTest
class DueDateReportTest {
	
	@Test
	void constructorTest()
	{		
		//default constructor
		DueDateReport ddr = new DueDateReport();
		assertTrue(ddr instanceof DueDateReport);
		
		//create a due date report with the list
		Date d1 = new Date();
		d1.setDate(25);
		d1.setMonth(2);
		d1.setYear(2022);
		
		Date d2 = new Date();
		d2.setDate(26);
		d2.setMonth(2);
		d2.setYear(2022);
		
		Assignment a1 = new Assignment("a1", d1, d2, 1, 1);
		Assignment a2 = new Assignment("a2", d1, d2, 1, 1);
		Assignment a3 = new Assignment("a3", d1, d2, 1, 1);
		Assignment a4 = new Assignment("a4", d1, d2, 1, 1);
		
		List<Assignment> aList = new ArrayList<Assignment>();
		aList.add(a1);
		aList.add(a2);
		aList.add(a3);
		aList.add(a4);
		
		ddr = new DueDateReport(aList);
		
		assertEquals(ddr.assignments.get(0), aList.get(0));
		assertEquals(ddr.assignments.get(1), aList.get(1));
		assertEquals(ddr.assignments.get(2), aList.get(2));
		assertEquals(ddr.assignments.get(3), aList.get(3));
	}
	
	@Test
	void getBestReleaseWindowTest()
	{
		//create a due date report with the list
		Date d1 = new Date(2022, 2 ,25);
		
		Date d2 = new Date(2022, 2, 26);
		
		Assignment a1 = new Assignment("a1", d1, d2, 1, 1);
		Assignment a2 = new Assignment("a2", d1, d2, 1, 1);
		Assignment a3 = new Assignment("a3", d1, d2, 1, 1);
		Assignment a4 = new Assignment("a4", d1, d2, 1, 1);
		
		List<Assignment> aList = new ArrayList<Assignment>();
		aList.add(a1);
		aList.add(a2);
		aList.add(a3);
		aList.add(a4);
		
		DueDateReport ddr = new DueDateReport(aList);
		
		ReleaseWindow rw = ddr.generateBestReleaseWindow(d1, d2);
		assertEquals(rw.priorityScore, 0);
		assertEquals(rw.getStartDate(), d1);
		assertEquals(rw.getEndDate(), d2);	
	}
	
	@Test
	void generateReleaseWindowsTest()
	{
		//create a report for march 1st 2022
		DueDateReport ddr = new DueDateReport(new Date(2022, 2, 1));
		assertNotNull(ddr.assignments);
		
		//System.out.println(new Date(2022, 2, 1, 0, 0) + " " + new Date(2022, 2, 1, 24, 0));
		List<ReleaseWindow> rw = ddr.generateReleaseWindows(new Date(2022, 2, 1, 0, 0), new Date(2022, 2, 1, 24, 0));
		assertNotNull(rw);
		//System.out.print(rw.get(0).getStartDate() + " " + rw.get(0).getEndDate());
		assertEquals(rw.size(), 93); // 24 hours in day * 4 = 96 quarter hours - 3 since you need a full hour to start the release so you can't start in the last 45 minutes
		assertTrue(rw.get(rw.size() - 1).startDate.compareTo(new Date(2022, 2, 1, 4, 0)) >= 0);
		assertTrue(rw.get(rw.size() - 1).startDate.compareTo(new Date(2022, 2, 1, 5, 0)) <= 0);
	}
}
