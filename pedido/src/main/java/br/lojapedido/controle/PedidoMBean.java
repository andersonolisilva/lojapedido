package br.lojapedido.controle;

import java.util.List;

import javax.faces.bean.ManagedBean;

import br.lojapedido.dao.PedidoDAO;
import br.lojapedido.dominio.Pedido;
import br.lojapedido.dominio.enumerador.SituacaoPedido;
import br.lojapedido.servico.PedidoService;

@ManagedBean
public class PedidoMBean extends AbstractController<Pedido> {
	
	private Pedido pedido = new Pedido();
	private PedidoService service;
	
	public List<Pedido> getListaCompleta() {
		PedidoDAO dao = new PedidoDAO();
		try {
			return dao.findAll();
		} finally {
			dao.close();
		}
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
		} finally {
			dao.close();
		}
		
		return null;
	}
	
	public String selecionar(Pedido pedido) {
		this.pedido = pedido;
		
		return null;
	}
	
	public String deletar(Pedido pedido) {
		PedidoDAO dao = new PedidoDAO();
		try {
			dao.delete(pedido);
			addSuccess("Pedido excluído com sucesso");
		} finally {
			dao.close();
		}
		
		return null;
	}
	
	public SituacaoPedido[] getAreas() {
		return SituacaoPedido.values();
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

}
