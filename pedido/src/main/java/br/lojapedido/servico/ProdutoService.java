package br.lojapedido.servico;

import java.math.BigDecimal;
import java.util.List;

import br.lojapedido.dao.ProdutoDAO;
import br.lojapedido.dominio.Produto;

public class ProdutoService implements ICRUD<Produto> {

	private ProdutoDAO produtoDAO;
	private Produto produto;

	public ProdutoService(ProdutoDAO dao, Produto produto) {
		this.produtoDAO = dao;
		this.produto = produto;
	}

	public void create() {
		try {
			validacaoDeProduto();
			produtoDAO.create(this.produto);
		} catch (Exception e) {
			throw new RuntimeException(
					"Revise a informação do produto e tente novamente: "+e.getMessage());
		}
	}

	public void update() {
		try {
			validacaoDeProduto();
			produtoDAO.update(this.produto);
		} catch (Exception e) {
			throw new RuntimeException(
					"Revise a informação do produto e tente novamente: "+e.getMessage());
		}
	}

	public void delete() {
		try {
			validacaoParaExclusaoDeProduto();
			produtoDAO.delete(this.produto);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public Produto findByPrimaryKey(int id) {
		return produtoDAO.findByPrimaryKey(id);
	}

	public List<Produto> findAll() {
		return produtoDAO.findAll();
	}
	
	private void validacaoParaExclusaoDeProduto(){
		if (produtoComPedido()){
			throw new RuntimeException("Não é possível excluir produto com pedido.");
		}
	}
	
	private boolean produtoComPedido(){
		return this.produto.getPedidosDoProduto().size()>0;
	}
	
	private void validacaoDeProduto(){
		if (produtoComEstoqueMenorZero()) {
			throw new RuntimeException("Produto não pode ter estoque menor que zero.");
		}
		if (produtoComValorMenorQueZero()) {
			throw new RuntimeException("Produto não pode ter valor menor que zero.");
		}
		if(produtoComDescricaoInvalida()){
			throw new RuntimeException("Produto com descrição inválida. Deve ter entre 1 e 255 caracteres.");
		}
		
	}
	/**
	 * Início: Validação de produtos
	 */
	private boolean produtoComEstoqueMenorZero(){
		boolean resultado = false;
		if (this.produto.getQuantidadeEstoque().compareTo(new BigDecimal("0"))< 0){
			resultado = true;
		}
		return resultado;
	}
	private boolean produtoComValorMenorQueZero(){
		boolean resultado = false;
		if (this.produto.getValor().compareTo(new BigDecimal("0")) < 0){
			resultado = true;
		}
		return resultado;
	}
	private boolean produtoComDescricaoInvalida(){
		/**
		 * Para ser considerada válida, a descrição deverá ter entre 1 e 255 caracteres.
		 */
		boolean resultado = false;
		if (this.produto.getDescricao().equals("")){
			resultado = true;
		}
		if(this.produto.getDescricao().length()>255){
			resultado = true;
		}
		return resultado;
	}
	/**
	 * Fim: Validação de produtos
	 */
}