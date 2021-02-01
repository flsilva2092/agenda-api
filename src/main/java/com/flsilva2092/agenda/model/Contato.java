package com.flsilva2092.agenda.model;

import com.flsilva2092.agenda.enumeration.TipoTelefoneEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Class that implements the Travel structure.
 * 
 * @author Francisco Silva
 * @since 24/08/2020
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contato {

	private Long id;
	private String nome;
	private TipoTelefoneEnum tipoTelefone;
	private String telefone;
	private LocalDate nascimento;
	private String endereco;
	private String cidade;
	private String estado;
	private String cep;

	public Contato(TipoTelefoneEnum tipoTelefone){
		this.tipoTelefone = tipoTelefone;
	}

}
