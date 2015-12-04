package br.lojapedido.controle;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.lojapedido.dao.ClienteDAO;
import br.lojapedido.dominio.Cliente;
import br.lojapedido.servico.ClienteService;

@ManagedBean
@ViewScoped
public class ClienteMBean extends AbstractController<Cliente> {
	
	private Cliente cliente = new Cliente();
	private ClienteService service;
	
	public List<Cliente> getListaCompleta() {
		ClienteDAO dao = new ClienteDAO();
		try {
			return dao.findAll();
		} finally {
			dao.close();
		}
	}
	
	public String salvar() {
		ClienteDAO dao = new ClienteDAO();
		service = new ClienteService(dao, cliente);
		
		try {
			if (cliente.getId() == 0) {
				service.create();
			} else {
				service.update();
			}
			
			cliente = new Cliente();
			addSuccess("Cliente salvo com sucesso");
		}catch(Exception e){
			addErro(e.getMessage());
		} finally {
			dao.close();
		}
		
		return null;
	}
	
	public String selecionar(Cliente cliente) {
		this.cliente = cliente;
		
		return null;
	}
	
	public String deletar(Cliente cliente) {
		ClienteDAO dao = new ClienteDAO();
		service = new ClienteService(dao, cliente);
		
		try {
			service.delete();
			addSuccess("Cliente excluido com sucesso");
		}catch(Exception e){
			addErro(e.getMessage());
		} finally {
			dao.close();
		}
		
		return null;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
