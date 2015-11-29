package br.lojapedido.dominio;

import java.math.BigDecimal;
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

import br.lojapedido.dao.PersistDB;
import br.lojapedido.dominio.enumerador.SituacaoPedido;

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
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public SituacaoPedido getSituacaoPedido() {
		return situacaoPedido;
	}
	public void setSituacaoPedido(SituacaoPedido situacaoPedido) {
		this.situacaoPedido = situacaoPedido;
	}
	public Date getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public BigDecimal getDesconto() {
		return desconto;
	}
	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}
	public BigDecimal getValorPedido() {
		return valorPedido;
	}
	public void setValorPedido(BigDecimal valorPedido) {
		this.valorPedido = valorPedido;
	}
	public List<PedidoItem> getItensDoPedido() {
		return itensDoPedido;
	}
	public void setItensDoPedido(List<PedidoItem> itensDoPedido) {
		this.itensDoPedido = itensDoPedido;
	}
	
	
}
