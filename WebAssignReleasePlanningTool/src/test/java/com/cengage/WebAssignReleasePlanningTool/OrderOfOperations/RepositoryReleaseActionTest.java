package com.cengage.WebAssignReleasePlanningTool.OrderOfOperations;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Time;

import org.junit.jupiter.api.Test;

class RepositoryReleaseActionTest {

	@Test
	void test() {
		Repository r = new Repository("test");
		RepositoryReleaseAction rra = new RepositoryReleaseAction("test", 15, r, null);
		
		assertEquals("test", rra.getName());
		assertEquals(15, rra.getDuration());
		assertEquals(r, rra.getRepository());
	}

}
