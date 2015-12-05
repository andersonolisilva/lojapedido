package br.lojapedido.converter;

import java.util.Collection;

import javax.faces.bean.ManagedBean;

import br.lojapedido.dao.ProdutoDAO;
import br.lojapedido.dominio.Produto;

@ManagedBean
public class ProdutoSelectOneMenu {

	ProdutoDAO dao = new ProdutoDAO();
	
	public Collection<Produto> findByAllProduto(){
		return dao.findAll();
	}
	
	public Produto findByProdutoById(int id){
		return (Produto) dao.findByPrimaryKey(id);
	}
	
}
