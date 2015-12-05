package br.lojapedido.teste;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ClientesSystemTest {

	private FirefoxDriver driver;
	
	@Before
	public void inicializar() {
		this.driver = new FirefoxDriver();
	}
	
	@Test
	public void deveAdicionarUmCliente() {
		ClientesPage clientes = new ClientesPage(driver);
		
		clientes.visita();
		clientes.cadastra("22222222222","Uira Kulesza","uirakulesza@gmail.com");
		
		assertTrue(clientes.existeNaListagem("22222222222","Uira Kulesza","uirakulesza@gmail.com"));
	}
	
	@After
	public void finalizar() {
		driver.close();
	}

}
