package br.lojapedido.dominio;

import java.util.ArrayList;
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
	private List<Pedido> pedidosDoCliente = new ArrayList<Pedido>();
	
	public Cliente(){}
	
	public Cliente(String nome, String cpf, String email) {
		this.setNome(nome);
		this.setCPF(cpf);
		this.setEmail(email);
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
	public void setCPF(String CPF) {
		CPF = CPF;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public List<Pedido> getPedidosDoCliente() {
		return pedidosDoCliente;
	}

	public void setPedidosDoCliente(List<Pedido> pedidosDoCliente) {
		this.pedidosDoCliente = pedidosDoCliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CPF == null) ? 0 : CPF.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime
				* result
				+ ((pedidosDoCliente == null) ? 0 : pedidosDoCliente.hashCode());
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
		Cliente other = (Cliente) obj;
		if (CPF == null) {
			if (other.CPF != null)
				return false;
		} else if (!CPF.equals(other.CPF))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (pedidosDoCliente == null) {
			if (other.pedidosDoCliente != null)
				return false;
		} else if (!pedidosDoCliente.equals(other.pedidosDoCliente))
			return false;
		return true;
	}
	
	
		
}
