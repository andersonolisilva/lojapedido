package br.lojapedido.dao;

import br.lojapedido.dominio.Pedido;


public class PedidoDAO extends GenericDAO<Pedido>{

	@Override
	public Class<Pedido> getClassType() {
		return Pedido.class;
	}

}
