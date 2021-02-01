package com.flsilva2092.agenda.ut;

import com.flsilva2092.agenda.service.ContatoService;
import com.flsilva2092.agenda.service.EstatisticaService;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.ZonedDateTime;

import static org.junit.Assert.*;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class ApiUnitTests {
	
	@Autowired
	private ContatoService contatoService;
	
	@Autowired
	private EstatisticaService estatisticaService;
	
	@BeforeAll
	public void setUp() {
		contatoService.createFactory();
		contatoService.criaContatoList();
	}

	@Test
	@Order(1)
	public void shouldReturnNotNullTravelService() {
		assertNotNull(contatoService);
	}

	@Test
	@Order(2)
	public void shouldReturnNotNullStatisticService() throws Exception {
		assertNotNull(estatisticaService);
	}


}
