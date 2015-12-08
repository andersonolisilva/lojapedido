package br.lojapedido.pedido;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PedidosPage {

	private final WebDriver driver;
	
	public PedidosPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void visita() {
		driver.get("http://localhost:8080/pedido/pedidoItem.xhtml");
	}
	
	public PedidosPage novoItem() {
		driver.findElement(By.id("pedido:NovoItem")).click();
		return new PedidosPage(driver);
	}
	
	public boolean existeNaListagem(String produto, double valorVenda, double quantidade, double subTotal) {
		return driver.getPageSource().contains(produto) &&
				driver.getPageSource().contains(String.valueOf(valorVenda)) &&
				driver.getPageSource().contains(String.valueOf(quantidade)) &&
				driver.getPageSource().contains(String.valueOf(subTotal));
	}
	
	public void cadastra(String produto, double valorVenda, double quantidade) {
		
		/*WebElement txtProduto = driver.findElement(By.id("pedido:produto"));*/
		
		WebElement txtValorVenda = driver.findElement(By.id("pedido:valorDoItem"));
		WebElement txtQuantidade = driver.findElement(By.id("pedido:quantidadeDoItem"));
		
		txtValorVenda.sendKeys(String.valueOf(valorVenda));
		txtQuantidade.sendKeys(String.valueOf(quantidade));
		
		Select cbProduto = new Select(driver.findElement(By.id("pedido:produto")));
		cbProduto.selectByVisibleText(produto);
		
//		driver.findElement(By.id("cliente:btnSalvar")).click();
		txtValorVenda.submit();
	}
	
	public void edita(String produto, double valorVenda, double quantidade) {
		driver.findElement(By.id("tabelaPedido:tableItemPedido:0:btnSelecionarItem"));
		
		WebElement txtValorVenda = driver.findElement(By.id("pedido:valorVenda"));
		WebElement txtQuantidade = driver.findElement(By.id("pedido:quantidade"));
		
		txtValorVenda.clear();
		txtValorVenda.sendKeys(String.valueOf(valorVenda));
		txtQuantidade.clear();
		txtQuantidade.sendKeys(String.valueOf(quantidade));
		
		Select cbProduto = new Select(driver.findElement(By.id("pedido:produto")));
		cbProduto.selectByVisibleText(produto);
		
		driver.findElement(By.id("pedido:btnSalvar")).click();
	}
	
	public void remove() {
		driver.findElement(By.id("tabelaPedido:tableItemPedido:0:btnExcluirItem")).click();
		driver.findElement(By.id("btnSim")).click();
		
//		driver.findElement(By.id("form:singleDT:0:j_idt45")).click();
		// falta descobrir como pegar botão do alert ou tentar colocar o confirm do primefaces!
	}
	
}
