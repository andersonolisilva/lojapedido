package br.lojapedido.servico;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import br.lojapedido.dao.ProdutoDAO;
import br.lojapedido.dominio.PedidoItem;
import br.lojapedido.dominio.Produto;
import br.lojapedido.servico.ProdutoService;

public class ProdutoServiceTest {

	@Test
	public void deveSalvarProdutoComEstoqueZero() {
		// Classes de Equivalência e Valor Limite: Caso de teste 7
		Produto produto = new Produto("Notebook Dell", new BigDecimal("0"),
				new BigDecimal("0"), new ArrayList<PedidoItem>());
		List<Produto> listaDeProdutos = Arrays.asList(produto);
		ProdutoDAO daoFalso = mock(ProdutoDAO.class);
		when(daoFalso.findAll()).thenReturn(listaDeProdutos);

		ProdutoService service = new ProdutoService(daoFalso, produto);
		service.create();

		assertEquals(1, daoFalso.findAll().size());
		assertEquals("Notebook Dell", daoFalso.findAll().get(0).getDescricao());
	}

	@Test
	public void deveAtualizarProdutoComEstoqueMaiorZero() {
		// Classes de Equivalência e Valor Limite: Caso de teste 4
		Produto produto = new Produto("Notebook Dell", new BigDecimal("0"),
				new BigDecimal("123"), new ArrayList<PedidoItem>());
		List<Produto> listaDeProdutos = Arrays.asList(produto);
		ProdutoDAO daoFalso = mock(ProdutoDAO.class);
		when(daoFalso.findAll()).thenReturn(listaDeProdutos);

		ProdutoService service = new ProdutoService(daoFalso, produto);
		service.update();

		assertEquals(1, daoFalso.findAll().size());
		assertEquals("Notebook Dell", daoFalso.findAll().get(0).getDescricao());
	}

	@Test(expected = RuntimeException.class)
	public void naoDeveSalvarProdutoComEstoqueMenorQueZero() {
		// Classes de Equivalência e Valor Limite: Caso de teste 8
		Produto produto = new Produto("Notebook Dell", new BigDecimal("0"),
				new BigDecimal("-1"), new ArrayList<PedidoItem>());
		List<Produto> listaDeProdutos = Arrays.asList(produto);
		ProdutoDAO daoFalso = mock(ProdutoDAO.class);
		when(daoFalso.findAll()).thenReturn(listaDeProdutos);
		ProdutoService service = new ProdutoService(daoFalso, produto);
		service.create();
		assertEquals(1, daoFalso.findAll().size());
	}

	@Test
	public void deveSalvarProdutoComValorZero() {
		// Classes de Equivalência e Valor Limite: Caso de teste 7
		Produto produto = new Produto("Notebook Dell", new BigDecimal("0"),
				new BigDecimal("0"), new ArrayList<PedidoItem>());
		List<Produto> listaDeProdutos = Arrays.asList(produto);
		ProdutoDAO daoFalso = mock(ProdutoDAO.class);
		when(daoFalso.findAll()).thenReturn(listaDeProdutos);
		ProdutoService service = new ProdutoService(daoFalso, produto);
		service.create();
		assertEquals(1, daoFalso.findAll().size());
		assertEquals("Notebook Dell", daoFalso.findAll().get(0).getDescricao());
	}

	@Test
	public void deveAtualizarProdutoComValorMaiorZero() {
		// Classes de Equivalência e Valor Limite: Caso de teste 4
		Produto produto = new Produto("Notebook Dell", new BigDecimal("123"),
				new BigDecimal("0"), new ArrayList<PedidoItem>());
		List<Produto> listaDeProdutos = Arrays.asList(produto);
		ProdutoDAO daoFalso = mock(ProdutoDAO.class);
		when(daoFalso.findAll()).thenReturn(listaDeProdutos);
		ProdutoService service = new ProdutoService(daoFalso, produto);
		service.update();
		assertEquals(1, daoFalso.findAll().size());
		assertEquals("Notebook Dell", daoFalso.findAll().get(0).getDescricao());
	}

	@Test(expected = Exception.class)
	public void naoDeveSalvarProdutoComValorMenorQueZero() {
		// Classes de Equivalência e Valor Limite: Caso de teste 8
		Produto produto = new Produto("Notebook Dell", new BigDecimal("-0.1"),
				new BigDecimal("0"), new ArrayList<PedidoItem>());
		List<Produto> listaDeProdutos = Arrays.asList(produto);
		ProdutoDAO daoFalso = mock(ProdutoDAO.class);
		when(daoFalso.findAll()).thenReturn(listaDeProdutos);
		ProdutoService service = new ProdutoService(daoFalso, produto);
		service.create();
	}

	@Test
	public void deveSalvarProdutoComDescricaoAPartirDeUmCaractere() {
		// Classes de Equivalência e Valor Limite: Caso de teste 8
		Produto produto = new Produto("abc", new BigDecimal("0"),
				new BigDecimal("0"), new ArrayList<PedidoItem>());
		List<Produto> listaDeProdutos = Arrays.asList(produto);
		ProdutoDAO daoFalso = mock(ProdutoDAO.class);
		when(daoFalso.findAll()).thenReturn(listaDeProdutos);
		ProdutoService service = new ProdutoService(daoFalso, produto);
		service.create();
		assertEquals(1, daoFalso.findAll().size());
		assertEquals("abc", daoFalso.findAll().get(0).getDescricao());
	}

	@Test(expected = RuntimeException.class)
	public void naoDeveAtualizarProdutoComDescricaoVazia() {
		// Classes de Equivalência e Valor Limite: Caso de teste 9
		Produto produto = new Produto("", new BigDecimal("0"), new BigDecimal(
				"0"), new ArrayList<PedidoItem>());
		List<Produto> listaDeProdutos = Arrays.asList(produto);
		ProdutoDAO daoFalso = mock(ProdutoDAO.class);
		when(daoFalso.findAll()).thenReturn(listaDeProdutos);
		ProdutoService service = new ProdutoService(daoFalso, produto);
		service.update();
		assertEquals(1, daoFalso.findAll().size());
	}

	@Test
	public void deveSalvarProdutoComDescricaoAte255Caractere() {
		// Classes de Equivalência e Valor Limite: Caso de teste 16
		Produto produto = new Produto(
				"NotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNot",
				new BigDecimal("0"), new BigDecimal("0"),
				new ArrayList<PedidoItem>());
		List<Produto> listaDeProdutos = Arrays.asList(produto);
		ProdutoDAO daoFalso = mock(ProdutoDAO.class);
		when(daoFalso.findAll()).thenReturn(listaDeProdutos);
		ProdutoService service = new ProdutoService(daoFalso, produto);
		service.create();
		assertEquals(1, daoFalso.findAll().size());
	}

	@Test(expected = RuntimeException.class)
	public void naoDeveSalvarProdutoComDescricaoSuperiorA255Caractere() {
		// Classes de Equivalência e Valor Limite: Caso de teste 17
		Produto produto = new Produto(
				"NotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNotebookDNote",
				new BigDecimal("0"), new BigDecimal("0"),
				new ArrayList<PedidoItem>());
		List<Produto> listaDeProdutos = Arrays.asList(produto);
		ProdutoDAO daoFalso = mock(ProdutoDAO.class);
		when(daoFalso.findAll()).thenReturn(listaDeProdutos);
		ProdutoService service = new ProdutoService(daoFalso, produto);
		service.create();
		assertEquals(1, daoFalso.findAll().size());
	}

	//Anderson Teste
	@Test
	public void deveExcluirProdutoSemPedido() {
		ProdutoDAO daoFalso = mock(ProdutoDAO.class);
		Produto produtoFalso = mock(Produto.class);
		List<PedidoItem> listaDePedidos = new ArrayList<PedidoItem>();
		when(produtoFalso.getPedidosDoProduto()).thenReturn(listaDePedidos);
		ProdutoService service = new ProdutoService(daoFalso, produtoFalso);
		service.delete();
	}

	@Test(expected = RuntimeException.class)
	public void naoDeveExcluirProdutoComPedido() {
		ProdutoDAO daoFalso = mock(ProdutoDAO.class);
		Produto produtoFalso = mock(Produto.class);
		PedidoItem pedido = new PedidoItem();
		pedido.setProduto(produtoFalso);
		List<PedidoItem> listaDePedidos = new ArrayList<PedidoItem>();
		listaDePedidos.add(pedido);
		when(produtoFalso.getPedidosDoProduto()).thenReturn(listaDePedidos);
		ProdutoService service = new ProdutoService(daoFalso, produtoFalso);
		service.delete();
	}

	@Test
	public void devePesquisarProdutoPorID() {
		ProdutoDAO daoFalso = mock(ProdutoDAO.class);
		Produto produto = new Produto();
		produto.setId(1);
		produto.setDescricao("Notebook Dell");
		produto.setQuantidadeEstoque(new BigDecimal("0"));
		produto.setValor(new BigDecimal("0"));
		when(daoFalso.findByPrimaryKey(1)).thenReturn(produto);
		ProdutoService service = new ProdutoService(daoFalso, produto);

		assertEquals(1, service.findByPrimaryKey(1).getId());
	}

	@Test
	public void devePesquisarTodosOsProdutos() {
		Produto produto1 = new Produto("abc", new BigDecimal("0"),
				new BigDecimal("0"), new ArrayList<PedidoItem>());
		Produto produto2 = new Produto("abcd", new BigDecimal("0"),
				new BigDecimal("0"), new ArrayList<PedidoItem>());

		List<Produto> listaDeProdutos = Arrays.asList(produto1, produto2);

		ProdutoDAO daoFalso = mock(ProdutoDAO.class);
		when(daoFalso.findAll()).thenReturn(listaDeProdutos);
		ProdutoService service = new ProdutoService(daoFalso, produto1);

		assertEquals(2, service.findAll().size());
	}

}