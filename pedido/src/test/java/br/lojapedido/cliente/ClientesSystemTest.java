package br.lojapedido.cliente;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ClientesSystemTest {

	private FirefoxDriver driver;
	private ClientesPage clientes;
	
	@Before
	public void inicializa() {
		this.driver = new FirefoxDriver();
		this.clientes = new ClientesPage(driver);
		this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void deveAdicionarUmCliente() {
		clientes.visita();
		clientes.cadastra("06788690499", "Daniel Olinto", "danielolinto31@gmail.com");
		
		assertTrue(clientes.existeNaListagem("06788690499", "Daniel Olinto", "danielolinto31@gmail.com"));
		
		clientes.remove("06788690499");
	}
	
	@Test
	public void deveEditarUmCliente() {
		clientes.visita();
		clientes.cadastra("06788690499", "Daniel Olinto", "danielolinto31@gmail.com");
		clientes.edita("06788690499", "Anderson Silva", "andersonolisilva@gmail.com");
		
		assertTrue(clientes.existeNaListagem("06788690499", "Anderson Silva", "andersonolisilva@gmail.com"));
		
		clientes.remove("06788690499");
	}
	
	@Test
	public void deveRemoverUmCliente() {
		clientes.visita();
		clientes.cadastra("06788690499", "Daniel Olinto", "danielolinto31@gmail.com");
		clientes.remove("06788690499");
		
		assertFalse(clientes.existeNaListagem("06788690499", "Daniel Olinto", "danielolinto31@gmail.com"));
	}

	@After
	public void finaliza() {
		driver.close();
	}

}
