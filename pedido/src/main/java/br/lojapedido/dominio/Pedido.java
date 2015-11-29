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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result
				+ ((dataPedido == null) ? 0 : dataPedido.hashCode());
		result = prime * result
				+ ((desconto == null) ? 0 : desconto.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((itensDoPedido == null) ? 0 : itensDoPedido.hashCode());
		result = prime * result
				+ ((situacaoPedido == null) ? 0 : situacaoPedido.hashCode());
		result = prime * result
				+ ((valorPedido == null) ? 0 : valorPedido.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (dataPedido == null) {
			if (other.dataPedido != null)
				return false;
		} else if (!dataPedido.equals(other.dataPedido))
			return false;
		if (desconto == null) {
			if (other.desconto != null)
				return false;
		} else if (!desconto.equals(other.desconto))
			return false;
		if (id != other.id)
			return false;
		if (itensDoPedido == null) {
			if (other.itensDoPedido != null)
				return false;
		} else if (!itensDoPedido.equals(other.itensDoPedido))
			return false;
		if (situacaoPedido != other.situacaoPedido)
			return false;
		if (valorPedido == null) {
			if (other.valorPedido != null)
				return false;
		} else if (!valorPedido.equals(other.valorPedido))
			return false;
		return true;
	}
	
	
	
}
