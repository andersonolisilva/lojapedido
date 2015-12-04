package br.lojapedido.dominio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import br.lojapedido.dao.PersistDB;

@Data
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
	@OneToMany(mappedBy="produto")
	private List<PedidoItem> pedidosDoProduto = new ArrayList<PedidoItem>();
	
	public Produto(){}
	
	public Produto(String descricao, BigDecimal valor, BigDecimal quantidadeEstoque,
			ArrayList<PedidoItem> pedidosDoProduto) {
		this.setDescricao(descricao);
		this.setValor(valor);
		this.setQuantidadeEstoque(quantidadeEstoque);
		this.setPedidosDoProduto(pedidosDoProduto);
	}
}