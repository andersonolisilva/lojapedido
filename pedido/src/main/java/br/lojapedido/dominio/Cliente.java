package br.lojapedido.dominio;

import java.io.Serializable;
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
public class Cliente implements Serializable, PersistDB{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8239442776847919299L;
	@Id
	@SequenceGenerator(name = "seq_cliente", initialValue = 1, allocationSize = 1, sequenceName = "seq_cliente")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_cliente")
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	private String nome;
	@Column(length=11, unique=true)
	private String CPF;
	private String email;
	@OneToMany(mappedBy="cliente")
	private List<Pedido> pedidosDoCliente = new ArrayList<Pedido>();
	
	public Cliente(){}
	
	public Cliente(String nome, String cpf, String email) {
		this.setNome(nome);
		this.setCPF(cpf);
		this.setEmail(email);
	}
	
}
