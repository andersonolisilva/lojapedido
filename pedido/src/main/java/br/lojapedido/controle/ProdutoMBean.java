package br.lojapedido.controle;

import java.util.List;

import javax.faces.bean.ManagedBean;

import br.lojapedido.dao.ProdutoDAO;
import br.lojapedido.dominio.Produto;

@ManagedBean
public class ProdutoMBean extends AbstractController<Produto> {
	
	private Produto produto = new Produto();
	
	public List<Produto> getListaCompleta() {
		ProdutoDAO dao = new ProdutoDAO();
		try {
			return dao.findAll();
		} finally {
			dao.close();
		}
	}
	
	public String salvar() {
		ProdutoDAO dao = new ProdutoDAO();
		
		try {
			if (produto.getId() == 0) {
				dao.create(produto);
			} else {
				dao.update(produto);
			}
			
			produto = new Produto();
			addSuccess("Produto salvo com sucesso");
		} finally {
			dao.close();
		}
		
		return null;
	}
	
	public String selecionar(Produto produto) {
		this.produto = produto;
		
		return null;
	}
	
	public String deletar(Produto produto) {
		ProdutoDAO dao = new ProdutoDAO();
		try {
			dao.delete(produto);
			addSuccess("Produto excluído com sucesso");
		} finally {
			dao.close();
		}
		
		return null;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
