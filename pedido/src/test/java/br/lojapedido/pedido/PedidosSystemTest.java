package br.lojapedido.pedido;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

public class PedidosSystemTest {

	private FirefoxDriver driver;
	private PedidosPage pedidos;
	
	@Before
	public void inicializa() {
		this.driver = new FirefoxDriver();
		this.pedidos = new PedidosPage(driver);
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
		driver.close();
	}

}
