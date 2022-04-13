package com.cengage.WebAssignReleasePlanningTool.OrderOfOperations;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReleaseTest {

	@Test
	void test() {
		Release r = new Release();
		
		//make sure lists are initialized
		assertNotNull(r.getReleaseActions());
		assertNotNull(r.getRollbackActions());
		
		//empty list = 0 duration
		assertEquals(0, r.getDuration());
		
		r = new Release("test");
		assertEquals("test", r.getName());
		
		r.setId(0);
		assertEquals(0, r.getId());
		
		//TODO: test adding actions
	}

}
