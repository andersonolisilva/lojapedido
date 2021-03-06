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

import lombok.Data;
import br.lojapedido.dao.PersistDB;

@Data
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
	private BigDecimal valorVenda = new BigDecimal("0");
	private BigDecimal quantidade = new BigDecimal("0");
	private BigDecimal subTotal = new BigDecimal("0");
	
	public PedidoItem(){}
	
	public PedidoItem(Pedido pedido, Produto produto,
			BigDecimal valorVenda, BigDecimal quantidade, BigDecimal subTotal) {
		this.setProduto(produto);
		this.setPedido(pedido);
		this.setValorVenda(valorVenda);
		this.setQuantidade(quantidade);
		this.setSubTotal(subTotal);
	}	
	
	public BigDecimal getSubTotal(){
		this.subTotal.add(this.quantidade.multiply(this.valorVenda));
		return this.subTotal;
	}
		
}
