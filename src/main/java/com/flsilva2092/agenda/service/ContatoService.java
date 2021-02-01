package com.flsilva2092.agenda.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flsilva2092.agenda.enumeration.TipoTelefoneEnum;
import com.flsilva2092.agenda.factory.ContatoFactory;
import com.flsilva2092.agenda.factory.impl.ContatoFactoryImpl;
import com.flsilva2092.agenda.model.Contato;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service that implements methods related to contatos.
 * 
 * @author Francisco Silva
 * @since 24/08/2020
 */
@Service
public class ContatoService {
	
	private ContatoFactory factory;
	
	private List<Contato> contatos;
	
	/**
	 * Metodo para criar Contato Factory
	 * 
	 * @author Francisco Silva
	 * @since 24/08/2020
	 */
	public void createFactory() {
		if(factory == null) {
			factory = new ContatoFactoryImpl();
		}
	}
	
	/**
	 * Metodo para criar lista de contatos
	 * 
	 * @author Francisco Silva
	 * @since 24/08/2020
	 */
	public void criaContatoList() {
		if(contatos == null) {
			contatos = new ArrayList<>();
		}
	}
	
	/**
	 * Method that check if JSON is valid.
	 * 
	 * @author Francisco Silva
	 * @since 24/08/2020
	 * 
	 * @param jsonInString
	 * @return boolean
	 */
	public boolean isJSONValid(String jsonInString) {
	    try {
	       return new ObjectMapper().readTree(jsonInString) != null;
	    } catch (IOException e) {
	       return false;
	    }
	}
	
	/**
	 * Method to parse the id field.
	 * 
	 * @author Francisco Silva
	 * @since 24/08/2020
	 * 
	 * @param contato
	 * @return long
	 */
	private long parseId(JSONObject contato) {
		return Long.valueOf((int) contato.get("id"));
	}
	
	
	/**
	 * Method to parse the startDate field.
	 * 
	 * @author Francisco Silva
	 * @since 24/08/2020
	 * 
	 * @param contato
	 * @return LocalDateTime
	 */
	private LocalDate parseNascimentoDate(JSONObject contato) {
		var nascimento = (String) contato.get("nascimento");
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
		return LocalDate.parse(nascimento, formatter);
	}

	/**
	 * Method that check if the contato is being finished in the future.
	 * 
	 * @author Francisco Silva
	 * @since 24/08/2020
	 * 
	 * @param contato
	 * @return boolean
	 */
//	public boolean isStartDateGreaterThanEndDate(contato contato) {
//		if (contato.getEndDate() == null) return false;
//		return contato.getStartDate().isAfter(contato.getEndDate());
//	}
	
	/**
	 * Method to fullfil the contato object
	 * 
	 * @author Francisco Silva
	 * @since 24/08/2020
	 * 
	 * @param jsoncontato
	 * @param contato
	 */
	private void setContatoValues(JSONObject jsoncontato, Contato contato) {
		
		String nome = (String) jsoncontato.get("nome");
		String tipoTelefone = (String) jsoncontato.get("tipo_telefone");
		
		contato.setNome (nome != null ? nome : contato.getNome());
		contato.setTipoTelefone(tipoTelefone != null ? TipoTelefoneEnum.getEnum(tipoTelefone) : contato.getTipoTelefone());
		contato.setTelefone(jsoncontato.get("telefone") != null ? (String) jsoncontato.get("telefone") : contato.getTelefone());
		contato.setNascimento(jsoncontato.get("nascimento") != null ? parseNascimentoDate(jsoncontato) : contato.getNascimento());
		contato.setEndereco(jsoncontato.get("endereco") != null ? (String) jsoncontato.get("endereco") : contato.getEndereco());
		contato.setCidade(jsoncontato.get("cidade") != null ? (String) jsoncontato.get("cidade") : contato.getCidade());
		contato.setEstado(jsoncontato.get("estado") != null ? (String) jsoncontato.get("estado") : contato.getEstado());
		contato.setCep(jsoncontato.get("CEP") != null ? (String) jsoncontato.get("CEP") : contato.getCep());
	}
	
	/**
	 * Method to create a trip
	 * 
	 * @author Francisco Silva
	 * @since 24/08/2020
	 * 
	 * @param jsoncontato
	 * @return contato
	 */
	public Contato create(JSONObject jsoncontato) {
		
		createFactory();

		Contato contato = factory.createContato((String) jsoncontato.get("type"));
		contato.setId(parseId(jsoncontato));
		setContatoValues(jsoncontato, contato);
		
		return contato;
	}
	
	/**
	 * Method to update a trip
	 * 
	 * @author Francisco Silva
	 * @since 24/08/2020
	 * 
	 * @param contato
	 * @param jsoncontato
	 * 
	 * @return contato
	 */
	public Contato update(Contato contato, JSONObject jsoncontato) {
		
		setContatoValues(jsoncontato, contato);
		return contato;
	}

	/**
	 * Method that add an object contato
	 * 
	 * @author Francisco Silva
	 * @since 24/08/2020
	 * 
	 * @param contato
	 */
	public void add(Contato contato) {
		criaContatoList();
		contatos.add(contato);
	}

	/**
	 * Method that get all trips
	 * 
	 * @author Francisco Silva
	 * @since 24/08/2020
	 * 
	 * @return List
	 */
	public List<Contato> find() {
		criaContatoList();
		return contatos;
	}
	
	/**
	 * Method that get contatos by id
	 * 
	 * @author Francisco Silva
	 * @since 24/08/2020
	 * 
	 * @param id
	 * @return Trip
	 */
	public Contato findById(long id) {
		return contatos.stream().filter(t -> id == t.getId()).collect(Collectors.toList()).get(0);
	}
	
	/**
	 * Method that deletes the contato created
	 * 
	 * @author Francisco Silva
	 * @since 24/08/2020
	 */
	public void delete() {
		contatos.clear();
	}
	
	/**
	 * Method to clean objects
	 * 
	 * @author Francisco Silva
	 * @since 24/08/2020
	 */
	public void clearObjects() {
		contatos = null;
		factory = null;
	}

}
