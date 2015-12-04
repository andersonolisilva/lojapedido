package br.lojapedido.controle;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.lojapedido.dao.ProdutoDAO;
import br.lojapedido.dominio.Produto;
import br.lojapedido.servico.ProdutoService;

@ManagedBean
@ViewScoped
public class ProdutoMBean extends AbstractController<Produto> {
	
	private Produto produto = new Produto();
	private ProdutoService service;
	
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
		service = new ProdutoService(dao, produto);
		
		try {
			if (produto.getId() == 0) {
				service.create();
			} else {
				service.update();
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
		service = new ProdutoService(dao, produto);
		
		try {
			service.delete();
			addSuccess("Produto exclu�do com sucesso");
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
