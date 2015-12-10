package br.lojapedido.produto;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.pedido.arquitetura.ArquiteturaTest;

public class ProdutosPage extends ArquiteturaTest {

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
		return driver.getPageSource().contains("Campo descricaoo e obrigatorio");
	}
	
	public void cadastra(String descricao, String valor, String quantidadeEstoque) {
		WebElement txtDescricao = driver.findElement(By.id("produto:descricao"));
		WebElement txtValor = driver.findElement(By.id("produto:valor"));
		WebElement txtQuantidadeEstoque = driver.findElement(By.id("produto:quantidadeEstoque"));
		
		txtDescricao.clear();
		txtDescricao.sendKeys(descricao);
		txtValor.clear();
		txtValor.sendKeys(valor);
		txtQuantidadeEstoque.clear();
		txtQuantidadeEstoque.sendKeys(quantidadeEstoque);
		
		clicarEmBotao(driver, "produto:btnSalvar");
	}
	
	public void edita(String descricao, String valor, String quantidadeEstoque) {
		int linha = indicarListaNaTabela(descricao);
		clicarEmBotao(driver,"tabelaProduto:singleDT:"+String.valueOf(linha)+":btnSelecionar");
		
		WebElement txtDescricao = driver.findElement(By.id("produto:descricao"));
		WebElement txtValor = driver.findElement(By.id("produto:valor"));
		WebElement txtQuantidadeEstoque = driver.findElement(By.id("produto:quantidadeEstoque"));
		
		txtDescricao.clear();
		txtDescricao.sendKeys(descricao);
		txtValor.clear();
		txtValor.sendKeys(valor);
		txtQuantidadeEstoque.clear();
		txtQuantidadeEstoque.sendKeys(quantidadeEstoque);

		clicarEmBotao(driver, "produto:btnSalvar");
	}
	
	public void remove(String descricao) {
		int linha = indicarListaNaTabela(descricao);
		clicarEmBotao(driver,"tabelaProduto:singleDT:"+String.valueOf(linha)+":btnExcluir");
		clicarEmBotao(driver, "btnSim");
	}
	
	public int indicarListaNaTabela(String CPF){
		int index = 0;
		WebElement baseTable = driver.findElement(By.className("ui-datatable-tablewrapper"));
		List<WebElement> tableRows = baseTable.findElements(By.tagName("tr"));
		for (int i = 1; i < tableRows.size(); i++) {
			if(tableRows.get(i).getText().contains(CPF)){
				index = i;
			}
		}
		if(index>0){
			index = index-1;
		}
		return index;
	}
	
}
