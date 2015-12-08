package br.lojapedido.cliente;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
	}
	
	@Test
	public void deveAdicionarUmCliente() {
		clientes.visita();
		clientes.cadastra("22222222222", "Uira Kulesza", "uirakulesza@gmail.com");
		
		assertTrue(clientes.existeNaListagem("22222222222", "Uira Kulesza", "uirakulesza@gmail.com"));
		
		clientes.remove();
	}
	
	@Test
	public void deveEditarUmCliente() {
		clientes.visita();
		clientes.cadastra("11111111111", "Uira Kulesza", "uirakulesza@gmail.com");
		clientes.edita("11111111111", "Uira Kulesza", "uirakulesza@hotmail.com");
		
		assertTrue(clientes.existeNaListagem("11111111111", "Uira Kulesza", "uirakulesza@hotmail.com"));
		
		clientes.remove();
	}
	
	@Test
	public void deveRemoverUmCliente() {
		clientes.visita();
		clientes.cadastra("11111111111", "Uira Kulesza", "uirakulesza@gmail.com");
		clientes.remove();
		
		assertFalse(clientes.existeNaListagem("11111111111", "Uira Kulesza", "uirakulesza@gmail.com"));
	}
	
	@Test
	public void deveVerificarCpfExistente() {
		clientes.visita();
		clientes.cadastra("33333333333", "Daniel Olinto", "danielolinto31@gmail.com");
		clientes.cadastra("33333333333", "Anderson Oliveira", "andersonolisilva@gmail.com");
		
		assertTrue(clientes.existeCpfDuplicado());
		
		clientes.remove();
	}
	
	@After
	public void finaliza() {
		driver.close();
	}

}
