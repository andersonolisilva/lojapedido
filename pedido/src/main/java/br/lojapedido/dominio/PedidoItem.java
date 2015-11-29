package br.lojapedido.dominio;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import br.lojapedido.dao.PersistDB;

@Entity
public class PedidoItem implements PersistDB{

	@Id
	@SequenceGenerator(name = "seq_pedido_item", initialValue = 1, allocationSize = 1, sequenceName = "seq_pedido_item")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_pedido_item")
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="pedidoId")
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name = "produtoId")
	private Produto produto;
	private BigDecimal valorVenda;
	private BigDecimal quantidade;
	private BigDecimal subTotal;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public BigDecimal getValorVenda() {
		return valorVenda;
	}
	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}
	public BigDecimal getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}
	public BigDecimal getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}
	
}
