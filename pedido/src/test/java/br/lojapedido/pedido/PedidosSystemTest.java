package br.lojapedido.pedido;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.lojapedido.produto.ProdutosPage;

public class PedidosSystemTest {

	private FirefoxDriver driver;
	private PedidosPage pedidos;
	private ProdutosPage produtos;
	
	@Before
	public void inicializa() {
		driver = new FirefoxDriver();
		pedidos = new PedidosPage(driver);
		produtos = new ProdutosPage(driver);
		this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		produtos.visita();
		produtos.cadastra("iPhone 6", "2800", "1");
	}
	
	@Test
	public void deveAdicionarUmItem() {
		pedidos.visita();
		pedidos.novoItem().cadastra("iPhone 6" , "1", "2800");
		
		assertTrue(pedidos.existeNaListagem("iPhone 6", "1", "2800", "2800"));
		
		pedidos.remove();
	}
	
	@Test
	public void deveEditarUmItem() {
		pedidos.visita();
		pedidos.novoItem().cadastra("iPhone 6", "1", "2800");
		pedidos.mudaItem().edita("iPhone 6", "2", "2800");
		
		assertTrue(pedidos.existeNaListagem("iPhone 6", "2", "2800", "5600"));
		
		pedidos.remove();
	}
	
	@Test
	public void deveRemoverUmItem() {
		pedidos.visita();
		pedidos.novoItem().cadastra("iPhone 6", "1", "2800");
		pedidos.remove();
		
		assertFalse(pedidos.existeNaListagem("iPhone 6", "1", "2800", "2800"));
	}
	
	@After
	public void finaliza() {
		produtos.visita();
		produtos.remove("iPhone 6");
		
		driver.close();
	}

}
