package com.cengage.WebAssignReleasePlanningTool.DueDateReport;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReleaseWindowTest {

	@Test
	void testCompareTo()
	{
		//test the 1 hour heuristic
		ReleaseWindow rw1 = new ReleaseWindow(new Date(2022, 2, 1, 12, 0), new Date(2022, 2, 2, 12, 0));
		ReleaseWindow rw2 = new ReleaseWindow(new Date(2022, 2, 1, 12, 0), new Date(2022, 2, 2, 12, 0));
		
		rw1.assignments.add(new Assignment("Homework A", new Date(2022, 2, 1, 12, 0), new Date(2022, 2, 1, 13, 15), 0, 0));
		rw2.assignments.add(new Assignment("Homework B", new Date(2022, 2, 1, 12, 0), new Date(2022, 2, 1, 13, 0), 0, 0));
		
		assertEquals(-1, rw1.compareTo(rw2));
		assertEquals(1, rw2.compareTo(rw1));
		assertEquals(0, rw1.compareTo(rw1));
		assertEquals(0, rw2.compareTo(rw2));
		
		//clear out the list
		rw1.assignments = new ArrayList<Assignment>();
		rw2.assignments = new ArrayList<Assignment>();
		
		//test the test/quiz/other comparison
		rw1.assignments.add(new Assignment("Test A", new Date(2022, 2, 1, 12, 0), new Date(2022, 2, 1, 18, 0), 0, 0));
		rw2.assignments.add(new Assignment("Quiz A", new Date(2022, 2, 1, 12, 0), new Date(2022, 2, 1, 18, 0), 0, 0));
		ReleaseWindow rw3 = new ReleaseWindow(new Date(2022, 2, 1, 12, 0), new Date(2022, 2, 2, 12, 0));
		rw3.assignments.add(new Assignment("Homework A", new Date(2022, 2, 1, 12, 0), new Date(2022, 2, 1, 18, 0), 0, 0));
		ReleaseWindow rw4 = new ReleaseWindow(new Date(2022, 2, 1, 12, 0), new Date(2022, 2, 2, 12, 0));
		rw4.assignments.add(new Assignment("Homework B", new Date(2022, 2, 1, 12, 0), new Date(2022, 2, 1, 18, 0), 0, 0));
		
		assertEquals(1, rw1.compareTo(rw2));
		assertEquals(1, rw1.compareTo(rw3));
		
		assertEquals(-1, rw2.compareTo(rw1));
		assertEquals(1, rw1.compareTo(rw3));
		
		assertEquals(-1, rw3.compareTo(rw3));
		assertEquals(-1, rw3.compareTo(rw3));
		
		assertEquals(0, rw3.compareTo(rw4));
		
		//test the scoring comparison
		//rw2.assignments() //right now rw1 score is 5 and rw2 score is 3, add a homework to rw2 makes it 5 and 4
	}

}
