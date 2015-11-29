package br.lojapedido.dao;

import br.lojapedido.dominio.Produto;


public class ProdutoDAO extends GenericDAO<Produto>{

	@Override
	public Class<Produto> getClassType() {
		return Produto.class;
	}

}
