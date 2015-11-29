package br.lojapedido.dominio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import br.lojapedido.dao.ClienteDAO;
import br.lojapedido.servico.ClienteService;

public class ClienteServiceTest {

	@Test
	public void deveSalvarCliente() {
		Cliente cliente = new Cliente("Anderson Silva", "09098776543",
				"andersonolisilva@gmail.com");
		ClienteDAO daoFalso = mock(ClienteDAO.class);
		List<Cliente> listaDeClientes = Arrays.asList(cliente);
		when(daoFalso.findAll()).thenReturn(listaDeClientes);
		ClienteService service = new ClienteService(daoFalso, cliente);
		service.create();
		assertEquals(1, daoFalso.findAll().size());
		assertEquals("Anderson Silva", daoFalso.findAll().get(0).getNome());
		assertEquals("09098776543", daoFalso.findAll().get(0).getCPF());
		assertEquals("andersonolisilva@gmail.com", daoFalso.findAll().get(0)
				.getEmail());
	}

	@Test(expected = RuntimeException.class)
	public void naoSalvaClienteComCPFAlfanumerico() {
		Cliente cliente = new Cliente("Anderson Silva", "A0909876543",
				"andersonolisilva@gmail.com");
		ClienteDAO daoFalso = mock(ClienteDAO.class);
		ClienteService service = new ClienteService(daoFalso, cliente);
		service.create();
	}
	
	@Test(expected = RuntimeException.class)
	public void naoSalvaClienteComCPFVazio(){
		Cliente cliente = new Cliente("Anderson Silva", "",
				"andersonolisilva@gmail.com");
		ClienteDAO daoFalso = mock(ClienteDAO.class);
		ClienteService service = new ClienteService(daoFalso, cliente);
		service.create();
	}
	
	@Test(expected = RuntimeException.class)
	public void naoSalvaClienteComCPFComQuantidadeMenorQue11Digitos(){
		Cliente cliente = new Cliente("Anderson Silva", "0987647589",
				"andersonolisilva@gmail.com");
		ClienteDAO daoFalso = mock(ClienteDAO.class);
		ClienteService service = new ClienteService(daoFalso, cliente);
		service.create();
	}
	
	@Test(expected = RuntimeException.class)
	public void naoSalvaClienteComCPFComQuantidadeMaiorQue11Digitos(){
		Cliente cliente = new Cliente("Anderson Silva", "098764758987",
				"andersonolisilva@gmail.com");
		ClienteDAO daoFalso = mock(ClienteDAO.class);
		ClienteService service = new ClienteService(daoFalso, cliente);
		service.create();
	}
	
	@Test(expected = RuntimeException.class)
	public void naoSalvaClienteComEmailInvalido(){
		Cliente cliente = new Cliente("Anderson Silva", "98764758987",
				"andersonolisilva#gmail.com");
		ClienteDAO daoFalso = mock(ClienteDAO.class);
		ClienteService service = new ClienteService(daoFalso, cliente);
		service.create();
	}
	
	@Test
	public void deveAtualizarCliente() {
		Cliente cliente = new Cliente("Anderson Silva", "09098776543",
				"andersonolisilva@gmail.com");
		ClienteDAO daoFalso = mock(ClienteDAO.class);
		List<Cliente> listaDeClientes = Arrays.asList(cliente);
		when(daoFalso.findAll()).thenReturn(listaDeClientes);
		ClienteService service = new ClienteService(daoFalso, cliente);
		service.update();
		assertEquals(1, daoFalso.findAll().size());
		assertEquals("Anderson Silva", daoFalso.findAll().get(0).getNome());
		assertEquals("09098776543", daoFalso.findAll().get(0).getCPF());
		assertEquals("andersonolisilva@gmail.com", daoFalso.findAll().get(0)
				.getEmail());
	}
	
	@Test(expected = RuntimeException.class)
	public void naoAtualizarClienteComEmailInvalido() {
		Cliente cliente = new Cliente("Anderson Silva", "09098776543",
				"andersonolisilva#gmail.com");
		ClienteDAO daoFalso = mock(ClienteDAO.class);
		ClienteService service = new ClienteService(daoFalso, cliente);
		service.update();
	}
	
	@Test
	public void deveExcluirClienteSemPedido() {
		ClienteDAO daoFalso = mock(ClienteDAO.class);
		Cliente clienteFalso = mock(Cliente.class);
		List<Pedido> listaDePedidos = new ArrayList<Pedido>();
		when(clienteFalso.getPedidosDoCliente()).thenReturn(listaDePedidos);
		ClienteService service = new ClienteService(daoFalso, clienteFalso);
		service.delete();
	}
	
	@Test(expected = RuntimeException.class)
	public void naoDeveExcluirClienteComPedido() {
		ClienteDAO daoFalso = mock(ClienteDAO.class);
		Cliente clienteFalso = mock(Cliente.class);
		Pedido pedido = new Pedido();
		pedido.setCliente(clienteFalso);		
		List<Pedido> listaDePedidos = new ArrayList<Pedido>();
		listaDePedidos.add(pedido);
		when(clienteFalso.getPedidosDoCliente()).thenReturn(listaDePedidos);
		ClienteService service = new ClienteService(daoFalso, clienteFalso);
		service.delete();
	}

	@Test
	public void devePesquisarClientePorID(){
		ClienteDAO daoFalso = mock(ClienteDAO.class);
		Cliente cliente = new Cliente();
		cliente.setId(1);
		cliente.setNome("Anderson Silva");
		cliente.setCPF("09876787652");
		cliente.setEmail("andersonolisilva@gmail.com");
		when(daoFalso.findByPrimaryKey(1)).thenReturn(cliente);
		ClienteService service = new ClienteService(daoFalso, cliente);
		
		assertEquals(1, service.findByPrimaryKey(1).getId());	
	}
	
	@Test
	public void devePesquisarTodosOsClientes(){
		Cliente cliente1 = new Cliente("Anderson Silva", "09098776543",
				"andersonolisilva@gmail.com");
		Cliente cliente2 = new Cliente("Daniel Olinto", "09098776543",
				"danielolinto31@gmail.com");
		
		List<Cliente> listaDeClientes = Arrays.asList(cliente1, cliente2);
		
		ClienteDAO daoFalso = mock(ClienteDAO.class);
		when(daoFalso.findAll()).thenReturn(listaDeClientes);
		ClienteService service = new ClienteService(daoFalso, cliente1);
		
		assertEquals(2, service.findAll().size());	
	}

}
