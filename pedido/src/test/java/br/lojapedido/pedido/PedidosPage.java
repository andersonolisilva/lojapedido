package br.lojapedido.pedido;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PedidosPage {

	private final WebDriver driver;
	
	public PedidosPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void visita() {
		driver.get("http://localhost:8080/pedido/pedidoItem.xhtml");
	}
	
	public NovoPedidoPage novoItem() {
		driver.findElement(By.id("pedido:NovoItem")).click();
		return new NovoPedidoPage(driver);
	}
	
	public NovoPedidoPage mudaItem() {
		driver.findElement(By.id("tabelaPedido:tableItemPedido:0:btnSelecionarItem")).click();
		return new NovoPedidoPage(driver);
	}
	
	public boolean existeNaListagem(String produto, Double quantidade, Double valorVenda, Double subTotal) {
		return driver.getPageSource().contains(produto) &&
				driver.getPageSource().contains(String.valueOf(quantidade)) &&
				driver.getPageSource().contains(String.valueOf(valorVenda)) &&
				driver.getPageSource().contains(String.valueOf(subTotal));
	}
	
	public void remove() {
		driver.findElement(By.id("tabelaPedido:tableItemPedido:0:btnExcluirItem")).click();
		driver.findElement(By.id("btnSim")).click();
		
//		driver.findElement(By.id("form:singleDT:0:j_idt45")).click();
		// falta descobrir como pegar botão do alert ou tentar colocar o confirm do primefaces!
	}
	
}
