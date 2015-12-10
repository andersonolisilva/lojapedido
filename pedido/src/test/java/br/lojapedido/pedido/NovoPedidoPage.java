package br.lojapedido.pedido;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import br.pedido.arquitetura.ArquiteturaTest;

public class NovoPedidoPage extends ArquiteturaTest{
	
	private final WebDriver driver;
	
	public NovoPedidoPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void cadastra(String produto, String quantidade, String valorVenda) {
		Select cbProduto = new Select(driver.findElement(By.name("pedido:produto_input")));
		cbProduto.selectByVisibleText(produto);
		
		WebElement txtQuantidade = driver.findElement(By.id("pedido:quantidadeDoItem"));
		WebElement txtValorVenda = driver.findElement(By.id("pedido:valorDoItem"));
		
		txtQuantidade.clear();
		txtQuantidade.sendKeys(quantidade);
		txtValorVenda.clear();
		txtValorVenda.sendKeys(valorVenda);
		
		clicarEmBotao(driver, "pedido:salvarItem");
	}
	
	public void edita(String produto, String quantidade, String valorVenda) {
		Select cbProduto = new Select(driver.findElement(By.id("pedido:produto_input")));
		cbProduto.selectByVisibleText(produto);
		
		WebElement txtQuantidade = driver.findElement(By.id("pedido:quantidadeDoItem"));
		WebElement txtValorVenda = driver.findElement(By.id("pedido:valorDoItem"));
		
		txtQuantidade.clear();
		txtQuantidade.sendKeys(quantidade);
		txtValorVenda.clear();
		txtValorVenda.sendKeys(valorVenda);
		
		clicarEmBotao(driver,"pedido:salvarItem" );
	}
}
