package br.lojapedido.pedido;

import org.openqa.selenium.WebDriver;

import br.pedido.arquitetura.ArquiteturaTest;

public class PedidosPage extends ArquiteturaTest{

	private final WebDriver driver;
	
	public PedidosPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void visita() {
		driver.get("http://localhost:8080/pedido/pedidoItem.xhtml");
	}
	
	public NovoPedidoPage novoItem() {
		clicarEmBotao(driver, "pedido:NovoItem");
		return new NovoPedidoPage(driver);
	}
	
	public NovoPedidoPage mudaItem() {
		clicarEmBotao(driver,"tabelaPedido:tableItemPedido:0:btnSelecionarItem");
		return new NovoPedidoPage(driver);
	}
	
	public boolean existeNaListagem(String produto, String quantidade, String valorVenda, String subTotal) {
		return driver.getPageSource().contains(produto) &&
				driver.getPageSource().contains(quantidade) &&
				driver.getPageSource().contains(valorVenda) &&
				driver.getPageSource().contains(subTotal);
	}
	
	public void remove() {
		clicarEmBotao(driver,"tabelaPedido:tableItemPedido:0:btnExcluirItem");
		clicarEmBotao(driver,"btnSim");
	}
	
}
