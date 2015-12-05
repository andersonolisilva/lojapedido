package br.lojapedido.dao;

import br.lojapedido.dominio.PedidoItem;


public class PedidoItemDAO extends GenericDAO<PedidoItem>{

	@Override
	public Class<PedidoItem> getClassType() {
		return PedidoItem.class;
	}

}
