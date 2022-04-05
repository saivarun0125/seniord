package com.cengage.WebAssignReleasePlanningTool.controllers;

import static org.junit.jupiter.api.Assertions.*;

import java.io.UnsupportedEncodingException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.hibernate.mapping.Map;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cengage.WebAssignReleasePlanningTool.WebAssignReleasePlanningToolApplication;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebAssignReleasePlanningToolApplication.class)
@WebAppConfiguration
@ActiveProfiles("test")
class DueDateReportControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @Autowired
    private ObjectMapper objectMapper;
    
	@Test
	void test() {
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		ResultActions ra = null;
		
		//first test day with no assignments
		try {
			ra = mockMvc.perform(MockMvcRequestBuilders.get("/duedatereport")
									.param("startDate", "1990-02-25T00:00:00.000Z")
									.param("endDate", "1990-02-26T23:59:59.000Z")
									.param("duration", "90"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertNotNull(ra);
		
		try {
			//System.err.println(ra.andReturn().getResponse().getContentAsString());
			ra.andExpect(jsonPath("items").isArray());
			
			int idx = 0;
			while(true)
			{
				try {
					ra.andExpect(jsonPath("items[" + idx + "].id").value(idx++));
				} 
				catch (AssertionError e)
				{
					if(e.getMessage().contains("No value at JSON path"))
					{
						break;	
					}
					else
					{
						fail();
					}
				}
			}
			
			System.out.println(ra.andReturn().getResponse().getContentAsString());
			
			ra.andExpect(jsonPath("rawData.rows").isArray()).andExpect(jsonPath("rawData.rows[0]").doesNotExist());  //no assignments = no rows
			
			assertEquals(2, idx);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}

}
