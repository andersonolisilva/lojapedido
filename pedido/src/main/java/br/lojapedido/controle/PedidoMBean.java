package br.lojapedido.controle;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.lojapedido.dao.PedidoDAO;
import br.lojapedido.dominio.Cliente;
import br.lojapedido.dominio.Pedido;
import br.lojapedido.dominio.PedidoItem;
import br.lojapedido.dominio.enumerador.SituacaoPedido;
import br.lojapedido.servico.PedidoService;

@ManagedBean
@ViewScoped
public class PedidoMBean extends AbstractController<Pedido> {

	private Pedido pedido = new Pedido(SituacaoPedido.ABERTO, Calendar
			.getInstance().getTime(), new Cliente(), new BigDecimal("0"),
			new BigDecimal("0"), new ArrayList<PedidoItem>());
	private PedidoService service;

	public List<Pedido> getListaCompleta() {
		PedidoDAO dao = new PedidoDAO();
		try {
			return dao.findAll();
		} finally {
			dao.close();
		}
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

}
