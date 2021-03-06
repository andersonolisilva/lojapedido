package br.lojapedido.produto;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ProdutosSystemTest {

	private FirefoxDriver driver;
	private ProdutosPage produtos;
	
	@Before
	public void inicializa() {
		this.driver = new FirefoxDriver();
		this.produtos = new ProdutosPage(driver);
		this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void deveAdicionarUmProduto() {
		produtos.visita();
		produtos.cadastra("iPhone 6", "2800", "1");
		
		assertTrue(produtos.existeNaListagem("iPhone 6", "2800", "1"));
		
		produtos.remove("iPhone 6");
	}
	
	@Test
	public void deveEditarUmProduto() {
		produtos.visita();
		produtos.cadastra("iPhone 6", "2800", "1");
		produtos.edita("iPhone 6", "2800", "3");
		
		assertTrue(produtos.existeNaListagem("iPhone 6", "2800", "3"));
		
		produtos.remove("iPhone 6");
	}
	
	@Test
	public void deveRemoverUmProduto() {
		produtos.visita();
		produtos.cadastra("iPhone 6", "2800", "1");
		produtos.remove("iPhone 6");
		
		assertFalse(produtos.existeNaListagem("iPhone 6", "2800", "1"));
	}
	/*
	@Test
	public void deveExistirDescricao() {
		produtos.visita();
		produtos.cadastra(null, "10", "50");
		
		assertTrue(produtos.existeDescricao());
	}
	*/
	
	@After
	public void finaliza() {
		driver.close();
	}

}
