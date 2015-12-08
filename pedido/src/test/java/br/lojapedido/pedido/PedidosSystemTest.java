package br.lojapedido.pedido;

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
		pedidos.novoItem().cadastra("iPhone 6" , 1.00 , 2800.00);
		
		assertTrue(pedidos.existeNaListagem("iPhone 6", 1.00 , 2800.00 , 2800.00));
		
		pedidos.remove();
	}
	
	/*@Test
	public void deveEditarUmItem() {
		pedidos.visita();
		pedidos.novoItem().cadastra("iPhone 6", 1.00, 2800.00);
		pedidos.mudaItem().edita("iPhone 6", 2.00, 2800.00);
		
		assertTrue(pedidos.existeNaListagem("iPhone 6", 2.00, 2800.00, 5600.00));
		
		pedidos.remove();
	}
	
	@Test
	public void deveRemoverUmItem() {
		pedidos.visita();
		pedidos.novoItem().cadastra("iPhone 6", 1.00, 2800.00);
		pedidos.remove();
		
		assertFalse(pedidos.existeNaListagem("iPhone 6", 1.00, 2800.00, 2800.00));
	}*/
	
	@After
	public void finaliza() {
		driver.close();
	}

}
