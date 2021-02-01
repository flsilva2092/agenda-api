package com.flsilva2092.agenda.controller;

import com.flsilva2092.agenda.model.Contato;
import com.flsilva2092.agenda.model.Estatistica;
import com.flsilva2092.agenda.service.ContatoService;
import com.flsilva2092.agenda.service.EstatisticaService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * SpringBoot RestController that creates all service end-points related to the statistics.
 * 
 * @author Francisco Silva
 * @since 24/08/2020
 */
@RestController
@RequestMapping("/agenda-api/estatistica")
public class EstatisticaController {
	
	private static final Logger logger = Logger.getLogger(EstatisticaController.class);
	
	@Autowired
	private ContatoService contatoService;
	
	@Autowired
	private EstatisticaService estatisticaService;
	
	
	/**
	 * Method that returns the statistics based on the trips
	 * 
	 * @author Francisco Silva
	 * @since 24/08/2020
	 * 
	 * @return ResponseEntity - 200
	 */
	@GetMapping(produces = { "application/json" })
	public ResponseEntity<Estatistica> getStatistics() {
		
		List<Contato> contatos = contatoService.find();
		Estatistica estatistica = estatisticaService.create(contatos);
		
		logger.info(estatistica);
		
		return ResponseEntity.ok(estatistica);
	}

}
