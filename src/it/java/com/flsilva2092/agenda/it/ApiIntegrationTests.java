package com.flsilva2092.agenda.it;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Class that implements API integration tests
 * 
 * @author Francisco Silva
 * @since 10/09/2019
 *
 */
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, MockitoTestExecutionListener.class })
public class ApiIntegrationTests {
	
	@Autowired
    private MockMvc mockMvc;

	@Test
	@Order(1)
	public void contextLoad() {
		assertNotNull(mockMvc);
	}
	
	@Test
	@Order(2)
	public void shouldReturnCreateTravel() throws Exception {

		JSONObject mapToCreate = setObjectToCreate();
		this.mockMvc.perform(post("/agenda-api/contato").contentType(MediaType.APPLICATION_JSON_VALUE)
        		.content(new ObjectMapper().writeValueAsString(mapToCreate))).andExpect(status().isCreated());
	}
	
	@Test
	@Order(3)
	public void shouldReturnUpdateTravel() throws Exception {
		
		JSONObject mapToUpdate = setObjectToUpdate();
		this.mockMvc.perform(put("/agenda-api/contato/1").contentType(MediaType.APPLICATION_JSON_VALUE)
        		.content(new ObjectMapper().writeValueAsString(mapToUpdate))).andExpect(status().isOk());
	}

	@Test
	@Order(4)
    public void shouldReturnGetAllTravels() throws Exception {
		this.mockMvc.perform(get("/agenda-api/contato")).andExpect(status().isOk());
    }
	
	@Test
	@Order(5)
    public void shouldReturnRemoveAllTravels() throws Exception {
		this.mockMvc.perform(delete("/agenda-api/contato")).andExpect(status().isNoContent());
    }
	
	@SuppressWarnings("unchecked")
	private JSONObject setObjectToCreate() {
		
		String startDate = "2019-11-21T09:59:51.312Z";
		String endDate = "2019-12-01T21:08:45.202Z";
		
		JSONObject map = new JSONObject();
		map.put("id", 1);
		map.put("orderNumber", "220788");
		map.put("amount", "22.88");
//		map.put("type", TravelTypeEnum.RETURN.getValue());
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		
		return map;
	}
	
	@SuppressWarnings("unchecked")
	private JSONObject setObjectToUpdate() {
		
		String startDate = "2019-11-21T09:59:51.312Z";
		
		JSONObject map = new JSONObject();
		map.put("id", 1L);
		map.put("orderNumber", "220788");
		map.put("amount", "22.88");
//		map.put("type", TravelTypeEnum.ONE_WAY.getValue());
		map.put("startDate", startDate);
		
		return map;
	}

}
