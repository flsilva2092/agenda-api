package com.flsilva2092.agenda.factory;

import com.flsilva2092.agenda.model.Contato;

/**
 * Interface that provides method for manipulate an object Travel.
 * 
 * @author Francisco Silva
 * @since 08/09/2019
 */
public interface ContatoFactory {

	Contato createContato (String tipoTelefone);
}
