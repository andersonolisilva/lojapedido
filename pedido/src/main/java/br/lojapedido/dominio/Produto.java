package br.lojapedido.dominio;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import br.lojapedido.dao.PersistDB;

@Entity
public class Produto implements PersistDB{

	@Id
	@SequenceGenerator(name = "seq_produto", initialValue = 1, allocationSize = 1, sequenceName = "seq_produto")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_produto")
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	private String descricao;
	private BigDecimal valor;
	private BigDecimal quantidadeEstoque;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public BigDecimal getQuantidadeEstoque() {
		return quantidadeEstoque;
	}
	public void setQuantidadeEstoque(BigDecimal quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}
	
	
}
