package com.cengage.WebAssignReleasePlanningTool.OrderOfOperations;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RepositoryTest {

	@Test
	void test() {
		Repository r = new Repository("Test");
		assertEquals("Test", r.getName());
		
		r.setId(0);
		assertEquals(0, r.getId());
		
		assertNotNull(r.getDependents());
		
		r.addDependent(new Repository("Dependent"));
		assertEquals(1, r.getDependents().size());
	}

}
