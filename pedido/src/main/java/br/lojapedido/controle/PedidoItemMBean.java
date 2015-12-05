package br.lojapedido.controle;

import java.math.BigDecimal;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.lojapedido.dao.PedidoItemDAO;
import br.lojapedido.dominio.Pedido;
import br.lojapedido.dominio.PedidoItem;
import br.lojapedido.dominio.Produto;

@ManagedBean
@ViewScoped
public class PedidoItemMBean extends AbstractController<PedidoItem> {

//	private PedidoItem pedidoItem = new PedidoItem();
	PedidoItem pedidoItem = new PedidoItem(new Pedido(), new Produto(), new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0"));

	public List<PedidoItem> getListaCompleta() {
		PedidoItemDAO dao = new PedidoItemDAO();
		try {
			return dao.findAll();
		} finally {
			dao.close();
		}
	}

	public String salvar() {
		PedidoItemDAO dao = new PedidoItemDAO();

		try {
			if (pedidoItem.getId() == 0) {
				dao.create(pedidoItem);
			} else {
				dao.update(pedidoItem);
			}

			pedidoItem = new PedidoItem();
			addSuccess("PedidoItem salvo com sucesso");
		} catch (Exception e) {
			addErro(e.getMessage());
		} finally {
			dao.close();
		}

		return null;
	}

	public String selecionar(PedidoItem pedidoItem) {
		this.pedidoItem = pedidoItem;

		return null;
	}

	public String deletar(PedidoItem pedidoItem) {
		PedidoItemDAO dao = new PedidoItemDAO();
		try {
			dao.delete(pedidoItem);
			addSuccess("PedidoItem excluido com sucesso");
		} catch (Exception e) {
			addErro(e.getMessage());
		} finally {
			dao.close();
		}

		return null;
	}

	public PedidoItem getPedidoItem() {
		return pedidoItem;
	}

	public void setPedidoItem(PedidoItem pedidoItem) {
		this.pedidoItem = pedidoItem;
	}

}
