package com.cengage.WebAssignReleasePlanningTool.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cengage.WebAssignReleasePlanningTool.WebAssignReleasePlanningToolApplication;
import com.cengage.WebAssignReleasePlanningTool.repositories.AssignmentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebAssignReleasePlanningToolApplication.class)
@WebAppConfiguration
public class ReleaseControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    
    @MockBean
    AssignmentRepository assignmentRepository;


    @Before
    public void setUp() {
      mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    
   @Test
   public void getAssignments() throws Exception {
	   //get all releases
	   mockMvc.perform(MockMvcRequestBuilders
	            .get("/release"))
	            .andExpect(status().isOk())
	            .andExpect(content()
	            	      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	   //get specific release
	   mockMvc.perform(MockMvcRequestBuilders
	            .get("/release/0"))
	            .andExpect(status().isOk())
	            .andExpect(content()
	            	      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	   
	   //add release
	   mockMvc.perform(MockMvcRequestBuilders
	            .post("/release/5?name=Test Release"))
	            .andExpect(status().isOk());
	   
	   //modify release
	   mockMvc.perform(MockMvcRequestBuilders
	            .put("/release/5?name=Test Release Modified"))
	            .andExpect(status().isOk());
   }
}
