package br.lojapedido.servico;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import br.lojapedido.dao.PedidoDAO;
import br.lojapedido.dominio.Cliente;
import br.lojapedido.dominio.Pedido;
import br.lojapedido.dominio.PedidoItem;
import br.lojapedido.dominio.Produto;
import br.lojapedido.dominio.enumerador.SituacaoPedido;

public class PedidoServiceTest {

	@Test
	public void deveSalvarPedido() {
		Cliente cliente = new Cliente("Anderson Silva", "12345678910",
				"andersonolisilva@gmail.com");
		Pedido pedido = new Pedido(SituacaoPedido.ABERTO, Calendar.getInstance().getTime(), cliente, new BigDecimal("0"), new BigDecimal("0"), new ArrayList<PedidoItem>());
				
		PedidoDAO daoFalso = mock(PedidoDAO.class);
		List<Pedido> listaDePedidos = Arrays.asList(pedido);
		when(daoFalso.findAll()).thenReturn(listaDePedidos);
		PedidoService service = new PedidoService(daoFalso, pedido);
		service.create();
		assertEquals(1, daoFalso.findAll().size());
		assertEquals(SituacaoPedido.ABERTO, daoFalso.findAll().get(0).getSituacaoPedido());
		assertEquals("12345678910", daoFalso.findAll().get(0).getCliente().getCPF());
	}

	@Test
	public void deveAtualizarPedido() {
		Cliente cliente = new Cliente("Anderson Silva", "12345678910",
				"andersonolisilva@gmail.com");
		Pedido pedido = new Pedido(SituacaoPedido.ABERTO, Calendar.getInstance().getTime(), cliente, new BigDecimal("0"), new BigDecimal("0"), new ArrayList<PedidoItem>());
				
		PedidoDAO daoFalso = mock(PedidoDAO.class);
		List<Pedido> listaDePedidos = Arrays.asList(pedido);
		when(daoFalso.findAll()).thenReturn(listaDePedidos);
		PedidoService service = new PedidoService(daoFalso, pedido);
		service.update();
		assertEquals(1, daoFalso.findAll().size());
		assertEquals(SituacaoPedido.ABERTO, daoFalso.findAll().get(0).getSituacaoPedido());
		assertEquals("12345678910", daoFalso.findAll().get(0).getCliente().getCPF());
	}
	
	@Test(expected=RuntimeException.class)
	public void naoDeveSalvarPedidoSemCliente() {
		Cliente cliente = null;
		Pedido pedido = new Pedido(SituacaoPedido.ABERTO, Calendar.getInstance().getTime(), cliente, new BigDecimal("0"), new BigDecimal("0"), new ArrayList<PedidoItem>());
		PedidoDAO daoFalso = mock(PedidoDAO.class);
		List<Pedido> listaDePedidos = Arrays.asList(pedido);
		when(daoFalso.findAll()).thenReturn(listaDePedidos);
		PedidoService service = new PedidoService(daoFalso, pedido);
		service.create();
	}
	
	@Test(expected=RuntimeException.class)
	public void naoDeveAtualizarPedidoComClienteComCPFInvalido() {
		Cliente cliente = new Cliente("Anderson Silva", "123456789101",
				"andersonolisilva@gmail.com");
		Pedido pedido = new Pedido(SituacaoPedido.ABERTO, Calendar.getInstance().getTime(), cliente, new BigDecimal("0"), new BigDecimal("0"), new ArrayList<PedidoItem>());
		PedidoDAO daoFalso = mock(PedidoDAO.class);
		List<Pedido> listaDePedidos = Arrays.asList(pedido);
		when(daoFalso.findAll()).thenReturn(listaDePedidos);
		PedidoService service = new PedidoService(daoFalso, pedido);
		service.update();
	}
	
	@Test
	public void deveExcluirClienteSemPedido() {
		PedidoDAO daoFalso = mock(PedidoDAO.class);
		Pedido pedidoFalso = mock(Pedido.class);
		List<PedidoItem> listaDeItens = new ArrayList<PedidoItem>();
		when(pedidoFalso.getItensDoPedido()).thenReturn(listaDeItens);
		PedidoService service = new PedidoService(daoFalso, pedidoFalso);
		service.delete();
	}
	
	@Test(expected = RuntimeException.class)
	public void naoDeveExcluirPedidoComItem() {
		PedidoDAO daoFalso = mock(PedidoDAO.class);
		Pedido pedidoFalso = mock(Pedido.class);
		PedidoItem pedidoItem = new PedidoItem();
		pedidoItem.setProduto(new Produto("Notebook Dell",new BigDecimal("0"),new BigDecimal("0"), new ArrayList<PedidoItem>()));
		pedidoItem.setQuantidade(new BigDecimal("1"));
		pedidoItem.setValorVenda(new BigDecimal("10"));
		pedidoItem.setSubTotal(new BigDecimal("10"));
		List<PedidoItem> listaDeItem = new ArrayList<PedidoItem>();
		listaDeItem.add(pedidoItem);
		when(pedidoFalso.getItensDoPedido()).thenReturn(listaDeItem);
		PedidoService service = new PedidoService(daoFalso, pedidoFalso);
		service.delete();
	}

	@Test
	public void devePesquisarPedidoPorID(){
		PedidoDAO daoFalso = mock(PedidoDAO.class);
		Pedido pedido = new Pedido();
		pedido.setId(1);
		pedido.setCliente(null);
		pedido.setDataPedido(Calendar.getInstance().getTime());
		when(daoFalso.findByPrimaryKey(1)).thenReturn(pedido);
		PedidoService service = new PedidoService(daoFalso, pedido);
		
		assertEquals(1, service.findByPrimaryKey(1).getId());	
	}
	
	@Test
	public void devePesquisarTodosOsClientes(){
		Cliente cliente = new Cliente("Anderson Silva", "12345678910",
				"andersonolisilva@gmail.com");
		Pedido pedido1 = new Pedido(SituacaoPedido.ABERTO, Calendar.getInstance().getTime(), cliente, new BigDecimal("0"), new BigDecimal("0"), new ArrayList<PedidoItem>());
		Pedido pedido2 = new Pedido(SituacaoPedido.ABERTO, Calendar.getInstance().getTime(), cliente, new BigDecimal("0"), new BigDecimal("0"), new ArrayList<PedidoItem>());
		
		List<Pedido> listaDePedidos = Arrays.asList(pedido1, pedido2);
		
		PedidoDAO daoFalso = mock(PedidoDAO.class);
		when(daoFalso.findAll()).thenReturn(listaDePedidos);
		PedidoService service = new PedidoService(daoFalso, pedido1);
		
		assertEquals(2, service.findAll().size());	
	}

}
