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
		pedidos.novoItem();
		pedidos.cadastra("iPhone 6 16GB", 2800.00, 1);
		
		assertTrue(pedidos.existeNaListagem("iPhone 6 16GB", 2800.00, 1, 2800.00));
		
		pedidos.remove();
	}
	
	@Test
	public void deveEditarUmItem() {
		pedidos.visita();
		pedidos.novoItem();
		pedidos.cadastra("iPhone 6 16GB", 2800.00, 1);
		pedidos.edita("iPhone 6 16GB", 2800.00, 2);
		
		assertTrue(pedidos.existeNaListagem("iPhone 6 16GB", 2800.00, 2, 5600.00));
		
		pedidos.remove();
	}
	
	@Test
	public void deveRemoverUmItem() {
		pedidos.visita();
		pedidos.novoItem();
		pedidos.cadastra("iPhone 6 16GB", 2800.00, 1);
		pedidos.remove();
		
		assertFalse(pedidos.existeNaListagem("iPhone 6 16GB", 2800.00, 1, 2800.00));
	}
	
	@After
	public void finaliza() {
		driver.close();
	}

}
