package br.lojapedido.dominio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import br.lojapedido.dao.PersistDB;
import br.lojapedido.dominio.enumerador.SituacaoPedido;

@Data
@Entity
public class Pedido implements PersistDB{

	@Id
	@SequenceGenerator(name = "seq_pedido", initialValue = 1, allocationSize = 1, sequenceName = "seq_pedido")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_pedido")
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	private SituacaoPedido situacaoPedido;
	private Date dataPedido;
	@ManyToOne
	@JoinColumn(name="clienteId")
	private Cliente cliente;
	private BigDecimal desconto;
	private BigDecimal valorPedido;
	
	@OneToMany(mappedBy="pedido")
	private List<PedidoItem> itensDoPedido;
	
	public Pedido(){}
	
	public Pedido(SituacaoPedido aberto, Date data, Cliente cliente,
			BigDecimal desconto, BigDecimal valorPedido,
			ArrayList<PedidoItem> pedidoItem) {
		this.setSituacaoPedido(aberto);
		this.setDataPedido(data);
		this.setCliente(cliente);
		this.setDesconto(desconto);
		this.setValorPedido(valorPedido);
		this.setItensDoPedido(pedidoItem);
	}	
	
}
