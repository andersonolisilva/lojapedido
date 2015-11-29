package br.lojapedido.servico;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.lojapedido.dao.ClienteDAO;
import br.lojapedido.dominio.Cliente;

public class ClienteService implements ICRUD<Cliente> {

	private ClienteDAO clienteDAO;
	private Cliente cliente;

	public ClienteService(ClienteDAO dao, Cliente cliente) {
		this.clienteDAO = dao;
		this.cliente = cliente;
	}

	public void create() {
		try {
			validacaoDeCliente();
			clienteDAO.create(this.cliente);
		} catch (Exception e) {
			throw new RuntimeException(
					"Revise a informação do cliente e tente novamente.");
		}
	}

	private void validacaoDeCliente() {
		if (!cpfDoClienteEhNumero()) {
			throw new RuntimeException("CPF informado não é um número válido.");
		}
		if (!cpfDoClientePossui11Digitos()) {
			throw new RuntimeException("CPF informado não possui 11 dígitos.");
		}
		if (!emailEhValido()) {
			throw new RuntimeException("Email inválido.");
		}
	}

	public void update() {
		try {
			validacaoDeCliente();
			clienteDAO.update(this.cliente);
		} catch (Exception e) {
			throw new RuntimeException(
					"Revise a informação do cliente e tente novamente.");
		}
	}

	public void delete() {
		try {
			validacaoParaExclusaoDeCliente();
			clienteDAO.delete(this.cliente);
		} catch (Exception e) {
			throw new RuntimeException("");
		}
	}

	public Cliente findByPrimaryKey(int id) {
		return clienteDAO.findByPrimaryKey(id);
	}

	public List<Cliente> findAll() {
		return clienteDAO.findAll();
	}

	private boolean cpfDoClienteEhNumero() {
		boolean ehNumero = false;
		@SuppressWarnings("unused")
		Double numero;
		try {
			numero = (Double.parseDouble(this.cliente.getCPF()));
			ehNumero = true;
		} catch (NumberFormatException e) {
			ehNumero = false;
		}
		return ehNumero;
	}

	private boolean cpfDoClientePossui11Digitos() {
		return this.cliente.getCPF().length() == 11;
	}

	public boolean emailEhValido() {
		// Expressão Regular para validar E-mail
		Pattern p = Pattern
				.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
		Matcher m = p.matcher(this.cliente.getEmail());
		return m.find();
	}
	
	private void validacaoParaExclusaoDeCliente(){
		
	}
}
