package br.lojapedido.dominio;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import br.lojapedido.dao.PersistDB;

@Entity
public class Cliente implements PersistDB{

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
	private List<Pedido> pedidosDoCliente;
	
	public Cliente(){}
	
	public Cliente(String nome, String cpf, String email) {
		this.nome = nome;
		this.CPF = cpf;
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}	
	
}
