package br.lojapedido.controle;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.lojapedido.dao.PedidoDAO;
import br.lojapedido.dominio.Cliente;
import br.lojapedido.dominio.Pedido;
import br.lojapedido.dominio.PedidoItem;
import br.lojapedido.dominio.enumerador.SituacaoPedido;
import br.lojapedido.servico.PedidoService;

@ManagedBean
@SessionScoped
public class PedidoMBean extends AbstractController<Pedido> {

	private Pedido pedido = new Pedido(SituacaoPedido.ABERTO, Calendar
			.getInstance().getTime(), new Cliente(), new BigDecimal("0"),
			new BigDecimal("0"), new ArrayList<PedidoItem>());
	private PedidoService service;
	private PedidoItem pedidoItem = new PedidoItem();

	public List<Pedido> getListaCompleta() {
		PedidoDAO dao = new PedidoDAO();
		try {
			return dao.findAll();
		} finally {
			dao.close();
		}
	}

	public PedidoItem getPedidoItem(){
		return this.pedidoItem;
	}
	
	public SituacaoPedido[] getListaSituacaoPedido() {
		return SituacaoPedido.values();
	}

	public String salvar() {
		PedidoDAO dao = new PedidoDAO();
		service = new PedidoService(dao, pedido);
		try {
			if (pedido.getId() == 0) {
				service.create();
			} else {
				service.update();
			}
			pedido = new Pedido();
			addSuccess("Pedido salvo com sucesso");
		} catch (Exception e) {
			addErro(e.getMessage());
		} finally {
			dao.close();
		}

		return "pedido";
	}

	public String selecionar(Pedido pedido) {
		this.pedido = pedido;

		return "pedidoItem";
	}

	public String deletar(Pedido pedido) {
		PedidoDAO dao = new PedidoDAO();
		try {
			dao.delete(pedido);
			addSuccess("Pedido excluido com sucesso");
		} catch (Exception e) {
			addErro(e.getMessage());
		} finally {
			dao.close();
		}
		return null;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public void novoItemPedido(){
		this.pedidoItem = new PedidoItem();
	}
	public void selecionarItemPedido(PedidoItem pedidoItem){
		this.pedidoItem = pedidoItem;
	}
	public void adicionarItemPedido(){
		this.pedidoItem.setPedido(this.pedido);
		if (this.pedido.getItensDoPedido().contains(this.pedidoItem)){
			this.pedido.getItensDoPedido().remove(this.pedidoItem);
		}
		this.pedidoItem.setSubTotal(calculaTotalItemPedido(this.pedidoItem));
		this.pedido.getItensDoPedido().add(this.pedidoItem);
		this.pedido.setValorPedido(calculaTotalDoPedido());
	}
	
	public void deletarItemPedido(PedidoItem pedidoItem){
		this.pedido.getItensDoPedido().remove(this.pedidoItem);
		this.pedido.setValorPedido(calculaTotalDoPedido());
	}
	
	public BigDecimal calculaTotalDoPedido(){
	    BigDecimal totalDoPedido = new BigDecimal("0"); 
	    for(PedidoItem item : this.pedido.getItensDoPedido()){
	    	if (item.getSubTotal().compareTo(new BigDecimal("0"))>0){
	    		totalDoPedido = totalDoPedido.add(item.getSubTotal());
	    	}
	    }
	    return totalDoPedido;
	}
	
	public BigDecimal calculaTotalItemPedido(PedidoItem pedidoItem){
		pedidoItem.setSubTotal(pedidoItem.getQuantidade().multiply(pedidoItem.getValorVenda()));
		return pedidoItem.getSubTotal();
	}

}
