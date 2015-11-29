package br.lojapedido.servico;

import java.util.List;

import br.lojapedido.dao.PedidoDAO;
import br.lojapedido.dominio.Pedido;

public class PedidoService implements ICRUD<Pedido>{

	private PedidoDAO pedidoDAO;
	private Pedido pedido;

	public PedidoService(PedidoDAO dao, Pedido pedido) {
		this.pedidoDAO = dao;
		this.pedido = pedido;
	}

	public void create() {
		try {
			validacaoDePedido();
			pedidoDAO.create(this.pedido);
		} catch (Exception e) {
			throw new RuntimeException(
					"Revise a informação do Pedido e tente novamente: "+e.getMessage());
		}
	}

	public void update() {
		try {
			validacaoDePedido();
			pedidoDAO.update(this.pedido);
		} catch (Exception e) {
			throw new RuntimeException(
					"Revise a informação do Pedido e tente novamente: "+e.getMessage());
		}
	}

	public void delete() {
		try {
			validacaoParaExclusaoDoPedido();
			pedidoDAO.delete(this.pedido);
		} catch (Exception e) {
			throw new RuntimeException("Não foi possível excluir o pedido: "+e.getMessage());
		}
	}

	public Pedido findByPrimaryKey(int id) {
		return pedidoDAO.findByPrimaryKey(id);
	}

	public List<Pedido> findAll() {
		return pedidoDAO.findAll();
	}
	
	private void validacaoDePedido(){
		if(pedidoSemCliente()){
			throw new RuntimeException("Pedido não possui cliente válido associado.");
		}
	}
	/*
	 * Início: Validação de pedido
	 */
	private boolean pedidoSemCliente(){
		boolean retorno = false;
		if (this.pedido.getCliente()==null || this.pedido.getCliente().getCPF().length()!=11){
			retorno = true;
		}
		return retorno;
	}
	/*
	 * Fim: Validação de pedido
	 */
	
	private void validacaoParaExclusaoDoPedido(){
		if(pedidoComItemProduto()){
			throw new RuntimeException("Pedido possui item associado.");
		}
	}
	/*
	 * Início: Validação de pedido
	 */
	private boolean pedidoComItemProduto(){
		boolean retorno = false;
		if (this.pedido.getItensDoPedido().size()>0){
			retorno = true;
		}
		return retorno;
	}
	/*
	 * Fim: Validação de pedido
	 */
	
}
