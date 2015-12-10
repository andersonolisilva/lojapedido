package br.lojapedido.cliente;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.pedido.arquitetura.ArquiteturaTest;

public class ClientesPage extends ArquiteturaTest{

	private final WebDriver driver;
	
	public ClientesPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void visita() {
		driver.get("http://localhost:8080/pedido/cliente.xhtml");
	}
	
	public boolean existeNaListagem(String cpf, String nome, String email) {	
		return driver.getPageSource().contains(cpf) &&
				driver.getPageSource().contains(nome) &&
				driver.getPageSource().contains(email);
	}
	
	public boolean existeCpfDuplicado() {
		return driver.getPageSource().contains("Revise a informação do cliente e tente novamente: Error while committing the transaction");
	}
	
	public void cadastra(String cpf, String nome, String email) {	
		WebElement txtCpf = driver.findElement(By.id("cliente:cpf"));
		WebElement txtNome = driver.findElement(By.id("cliente:nome"));
		WebElement txtEmail = driver.findElement(By.id("cliente:email"));
		
		txtCpf.clear();
		txtCpf.sendKeys(cpf);
		txtNome.clear();
		txtNome.sendKeys(nome);
		txtEmail.clear();
		txtEmail.sendKeys(email);
		
		clicarEmBotao(driver,"cliente:btnSalvar");
	}

	
	
	public void edita(String CPF, String nome, String email) {
		
		int linha = indicarListaNaTabela(CPF);
		clicarEmBotao(driver,"tabelaCliente:singleDT:"+String.valueOf(linha)+":btnSelecionar");
		
		WebElement txtNome = driver.findElement(By.id("cliente:nome"));
		WebElement txtEmail = driver.findElement(By.id("cliente:email"));
		txtNome.clear();
		txtNome.sendKeys(nome);
		txtEmail.clear();
		txtEmail.sendKeys(email);
		clicarEmBotao(driver, "cliente:btnSalvar");
		
	}

	public void remove(String CPF) {
		int linha = indicarListaNaTabela(CPF);
		clicarEmBotao(driver,"tabelaCliente:singleDT:"+String.valueOf(linha)+":btnExcluir");
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
