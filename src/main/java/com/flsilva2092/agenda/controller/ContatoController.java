package com.flsilva2092.agenda.controller;

import com.flsilva2092.agenda.model.Contato;
import com.flsilva2092.agenda.service.ContatoService;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

/**
 * SpringBoot RestController that creates all service end-points related to contatos.
 * 
 * @author Francisco Silva
 * @since 24/08/2020
 */
@RestController
@RequestMapping("/agenda-api/contato")
public class ContatoController {
	
	private static final Logger logger = Logger.getLogger(ContatoController.class);
	
	@Autowired
	private ContatoService contatoService;
	
	/**
	 * Method that list all contatos
	 * 
	 * @author Francisco Silva
	 * @since 24/08/2020
	 * 
	 * @return ResponseEntity with a <code>List<Contato></code> object and the HTTP status
	 * 
	 * HTTP Status:
	 * 
	 * 200 - OK: Everything worked as expected.
	 * 404 - Not Found: The requested resource doesn't exist.
	 * 
	 */
	@GetMapping
	public ResponseEntity<List<Contato>> find() {
		if(contatoService.find().isEmpty()) {
			return ResponseEntity.notFound().build(); 
		}
		logger.info(contatoService.find());
		return ResponseEntity.ok(contatoService.find());
	}
	
	/**
	 * Method that deletes all existing contatos.
	 * 
	 * @author Francisco Silva
	 * @since 24/08/2020
	 * 
	 * @return Returns an empty body with one of the following:
	 * 
	 * 204 - if delete with success
	 * 205 - if hasn't delete with success.
	 * 500 - Server Errors: something went wrong on API end (These are rare).
	 */
	@DeleteMapping
	public ResponseEntity<Boolean> delete() {
		try {
			contatoService.delete();
			return ResponseEntity.noContent().build();
		}catch(Exception e) {
			logger.error(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	/**
	 * Method that creates a contato.
	 * 
	 * @author Francisco Silva
	 * @since 24/08/2020
	 * 
	 * @param contato, where: 
	 * 
	 * id - contato id; 
	 * 
	 * @return ResponseEntity with a <code>Contato</code> object and the HTTP status
	 * 
	 * HTTP Status:
	 * 
	 * 201 - Created: Everything worked as expected.
	 * 400 - Bad Request: The request was unacceptable, often due to missing a required parameter.
	 * 422 - Unprocessable Entity: if any of the fields are not parsable or the initial date is greater than final date.
	 * 500 - Server Errors: something went wrong on API end (These are rare).
	 * 
	 */
	@PostMapping
	@ResponseBody
	public ResponseEntity<Contato> create(@RequestBody JSONObject contato) {
		try {
			if(contatoService.isJSONValid(contato.toString())) {
				Contato contatoCriado = contatoService.create(contato);
				var uri = ServletUriComponentsBuilder.fromCurrentRequest()
						.path(contatoCriado.getNome()).build().toUri();
				
				if(false){ // contatoCriado.isStartDateGreaterThanEndDateervice(contatoCriado)
					logger.error("The start date is greater than end date.");
					return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
				}else {
					contatoService.add(contatoCriado);
					return ResponseEntity.created(uri).body(null);
				}
			}else {
				return ResponseEntity.badRequest().body(null);
			}
		}catch(Exception e) {
			logger.error("JSON fields are not parsable. " + e);
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}
	
	/**
	 * Method that updates a contato.
	 * 
	 * @author Francisco Silva
	 * @since 24/08/2020
	 * 
	 * id - contato id;
	 * 
	 * @return Returns an empty body with one of the following:
	 * 200 – OK: Everything worked as expected.
	 * 400 - Bad Request: The request was unacceptable, often due to missing a required parameter.
	 * 404 - Not Found: The requested resource doesn't exist.
	 * 422 - Unprocessable Entity: if any of the fields are not parsable or the initial date is greater than final date.
	 * 500 - Server Errors: something went wrong on API end (These are rare).
	 */
	@PutMapping(path = "/{id}", produces = { "application/json" })
	public ResponseEntity<Contato> update(@PathVariable("id") long id, @RequestBody JSONObject contato) {
		try {
			if(contatoService.isJSONValid(contato.toString())) {
				Contato contatoToUpdate = contatoService.findById(id);
				if(contatoToUpdate == null){
					logger.error("Contato not found.");
					return ResponseEntity.notFound().build(); 
				}else {
					Contato contatoUpdated = contatoService.update(contatoToUpdate, contato);
					return ResponseEntity.ok(contatoUpdated);
				}
			}else {
				return ResponseEntity.badRequest().body(null);
			}
		}catch(Exception e) {
			logger.error("JSON fields are not parsable." + e);
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}
}
