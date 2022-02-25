/**
 * 
 */
package com.cengage.WebAssignReleasePlanningTool.DueDateReport;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Testing class for the Assignment class
 * @author snaru
 *
 */
@SpringBootTest
class AssignmentTest {

	/**
	 * Assignment objects for testing purposes
	 */
	private Assignment a, b = null;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	void setUp() throws Exception {
		a = new Assignment();
		Date end = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2020-03-14 12:00:00");
		Date start = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2020-04-15 12:30:00");
		b = new Assignment("Test", start, end, 1, 30);
	}

	/**
	 * Test method for {@link com.cengage.WebAssignReleasePlanningTool.DueDateReport.Assignment#Assignment()}.
	 */
	@Test
	void testAssignment() {
		assertNotNull(a);
	}

	/**
	 * Test method for {@link com.cengage.WebAssignReleasePlanningTool.DueDateReport.Assignment#Assignment(java.lang.String, java.util.Date, java.util.Date, int, int)}.
	 */
	@Test
	void testAssignmentStringDateDateIntInt() {
		assertNotNull(b);
	}

	/**
	 * Test method for {@link com.cengage.WebAssignReleasePlanningTool.DueDateReport.Assignment#getAssignmentId()}.
	 */
	@Test
	void testGetAssignmentId() {
		assertNull(b.getAssignmentId());
	}

	/**
	 * Test method for {@link com.cengage.WebAssignReleasePlanningTool.DueDateReport.Assignment#setCategory(java.lang.String)}.
	 */
	@Test
	void testSetCategory() {
		String newCategory = "Homework";
		assertNull(a.getCategory());
		a.setCategory(newCategory);
		assertNotNull(a.getCategory());
	}

	/**
	 * Test method for {@link com.cengage.WebAssignReleasePlanningTool.DueDateReport.Assignment#getCategory()}.
	 */
	@Test
	void testGetCategory() {
		String newCategory = "Homework";
		a.setCategory(newCategory);
		assertEquals("Homework", a.getCategory());
	}

	/**
	 * Test method for {@link com.cengage.WebAssignReleasePlanningTool.DueDateReport.Assignment#setStartDate(java.util.Date)}.
	 * @throws ParseException if test fails
	 */
	@Test
	void testSetStartDate() throws ParseException {
		Date newStart = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2020-02-13 17:45:00");
		assertNull(a.getStartDate());
		a.setStartDate(newStart);
		assertNotNull(a.getStartDate());

	}

	/**
	 * Test method for {@link com.cengage.WebAssignReleasePlanningTool.DueDateReport.Assignment#getStartDate()}.
	 * @throws ParseException if test fails
	 */
	@Test
	void testGetStartDate() throws ParseException {
		Date newStart = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2020-02-13 17:45:00");
		assertNull(a.getStartDate());
		a.setStartDate(newStart);
		assertEquals(newStart, a.getStartDate());
	}

	/**
	 * Test method for {@link com.cengage.WebAssignReleasePlanningTool.DueDateReport.Assignment#setEndDate(java.util.Date)}.
	 * @throws ParseException if test fails
	 */
	@Test
	void testSetEndDate() throws ParseException {
		Date endStart = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2020-05-16 06:15:00");
		assertNull(a.getEndDate());
		a.setStartDate(endStart);
		assertNotNull(a.getEndDate());
	}

	/**
	 * Test method for {@link com.cengage.WebAssignReleasePlanningTool.DueDateReport.Assignment#getEndDate()}.
	 * @throws ParseException if test fails
	 */
	@Test
	void testGetEndDate() throws ParseException {
		Date endStart = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2020-05-16 06:15:00");
		assertNull(a.getEndDate());
		a.setStartDate(endStart);
		assertEquals(endStart, a.getEndDate());
	}

	/**
	 * Test method for {@link com.cengage.WebAssignReleasePlanningTool.DueDateReport.Assignment#setDaysAvailable(int)}.
	 */
	@Test
	void testSetDaysAvailable() {
		int newDaysAvailable = 10;
		b.setDaysAvailable(newDaysAvailable);
		assertNotEquals(1, b.getDaysAvailable());
	}

	/**
	 * Test method for {@link com.cengage.WebAssignReleasePlanningTool.DueDateReport.Assignment#getDaysAvailable()}.
	 */
	@Test
	void testGetDaysAvailable() {
		int newDaysAvailable = 10;
		assertEquals(1, b.getDaysAvailable());
		b.setDaysAvailable(newDaysAvailable);
		assertNotEquals(10, b.getDaysAvailable());
	}

	/**
	 * Test method for {@link com.cengage.WebAssignReleasePlanningTool.DueDateReport.Assignment#setRosterCount(int)}.
	 */
	@Test
	void testSetRosterCount() {
		int newRosterCount = 10;
		b.setRosterCount(newRosterCount);
		assertNotEquals(30, b.getRosterCount());
	}

	/**
	 * Test method for {@link com.cengage.WebAssignReleasePlanningTool.DueDateReport.Assignment#getRosterCount()}.
	 */
	@Test
	void testGetRosterCount() {
		int newRosterCount = 10;
		assertEquals(30, b.getRosterCount());
		b.setRosterCount(newRosterCount);
		assertNotEquals(10, b.getRosterCount());
	}

	/**
	 * Test method for {@link com.cengage.WebAssignReleasePlanningTool.DueDateReport.Assignment#toString()}.
	 */
	@Test
	void testToString() {
		assertEquals(null, a.toString());
		assertEquals(null, b.toString());
	}

	/**
	 * Test method for {@link com.cengage.WebAssignReleasePlanningTool.DueDateReport.Assignment#compareTo(com.cengage.WebAssignReleasePlanningTool.DueDateReport.Assignment)}.
	 */
	@Test
	void testCompareTo() {
		assertNotEquals(0, a.compareTo(b));
		assertEquals(0, a.compareTo(a));
		assertEquals(0, b.compareTo(b));
	}

}
