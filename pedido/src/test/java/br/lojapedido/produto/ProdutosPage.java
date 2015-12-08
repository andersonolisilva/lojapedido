package br.lojapedido.produto;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProdutosPage {

	private final WebDriver driver;
	
	public ProdutosPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void visita() {
		driver.get("http://localhost:8080/pedido/produto.xhtml");
	}
	
	public boolean existeNaListagem(String descricao, String valor, String quantidadeEstoque) {
		return driver.getPageSource().contains(descricao) &&
				driver.getPageSource().contains(valor) &&
				driver.getPageSource().contains(quantidadeEstoque);
	}
	
	public boolean existeDescricao() {
		return driver.getPageSource().contains("Campo descrição é obrigatório");
	}
	
	public void cadastra(String descricao, String valor, String quantidadeEstoque) {
		WebElement txtDescricao = driver.findElement(By.id("produto:descricao"));
		WebElement txtValor = driver.findElement(By.id("produto:valor"));
		WebElement txtQuantidadeEstoque = driver.findElement(By.id("produto:quantidadeEstoque"));
		
		txtDescricao.sendKeys(descricao);
		txtValor.sendKeys(valor);
		txtQuantidadeEstoque.sendKeys(quantidadeEstoque);
		
		driver.findElement(By.id("produto:btnSalvar")).click();
	}
	
	public void edita(String descricao, String valor, String quantidadeEstoque) {
		driver.findElement(By.id("tabelaProduto:singleDT:0:btnSelecionar"));
		
		WebElement txtDescricao = driver.findElement(By.id("produto:descricao"));
		WebElement txtValor = driver.findElement(By.id("produto:valor"));
		WebElement txtQuantidadeEstoque = driver.findElement(By.id("produto:quantidadeEstoque"));
		
		txtDescricao.clear();
		txtDescricao.sendKeys(descricao);
		txtValor.clear();
		txtValor.sendKeys(valor);
		txtQuantidadeEstoque.clear();
		txtQuantidadeEstoque.sendKeys(quantidadeEstoque);
		
		driver.findElement(By.id("produto:btnSalvar")).click();
	}
	
	public void remove() {
		driver.findElement(By.id("tabelaProduto:singleDT:0:btnExcluir")).click();
		driver.findElement(By.id("btnSim")).click();
	}
	
}
