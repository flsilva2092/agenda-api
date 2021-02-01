package com.flsilva2092.agenda.factory.impl;

import com.flsilva2092.agenda.enumeration.TipoTelefoneEnum;
import com.flsilva2092.agenda.factory.ContatoFactory;
import com.flsilva2092.agenda.model.Contato;

/**
 * Factory class for the Travel entity.
 * 
 * @author Francisco Silva
 * @since 08/09/2019
 */
public class ContatoFactoryImpl implements ContatoFactory {

	@Override
	public Contato createContato(String tipoTelefone) {
		if (TipoTelefoneEnum.RESIDENCIAL.getValue().equals(tipoTelefone)) {
			return new Contato(TipoTelefoneEnum.RESIDENCIAL);
		} else if (TipoTelefoneEnum.TRABALHO.getValue().equals(tipoTelefone)) {
			return new Contato(TipoTelefoneEnum.TRABALHO);
		}

		return new Contato(TipoTelefoneEnum.AMBOS);
	}
}
