package br.lojapedido.converter;

import java.util.Collection;

import javax.faces.bean.ManagedBean;

import br.lojapedido.dao.ClienteDAO;
import br.lojapedido.dominio.Cliente;

@ManagedBean
public class ClienteSelectOneMenu {

	ClienteDAO dao = new ClienteDAO();
	
	public Collection<Cliente> findByAllBanco(){
		return dao.findAll();
	}
	
	public Cliente findByClienteById(int id){
		return (Cliente) dao.findByPrimaryKey(id);
	}
	
}
