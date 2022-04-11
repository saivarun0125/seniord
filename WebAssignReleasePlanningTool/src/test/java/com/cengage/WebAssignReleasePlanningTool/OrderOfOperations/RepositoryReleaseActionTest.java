package com.cengage.WebAssignReleasePlanningTool.OrderOfOperations;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Time;

import org.junit.jupiter.api.Test;

class RepositoryReleaseActionTest {

	@Test
	void test() {
		Time t = new Time(0,0,0);
		Repository r = new Repository("test");
		RepositoryReleaseAction rra = new RepositoryReleaseAction("test", t, r, null);
		
		assertEquals("test", rra.getName());
		assertEquals(t, rra.getDuration());
		assertEquals(r, rra.getRepository());
	}

}
