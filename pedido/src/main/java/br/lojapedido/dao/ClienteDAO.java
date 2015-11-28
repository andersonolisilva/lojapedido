package br.lojapedido.dao;

import br.lojapedido.dominio.Cliente;


public class ClienteDAO extends GenericDAO<Cliente>{

	@Override
	public Class<Cliente> getClassType() {
		return Cliente.class;
	}

}
