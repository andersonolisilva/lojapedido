package br.lojapedido.cliente;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ClientesPage {

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
		
		txtCpf.sendKeys(cpf);
		txtNome.sendKeys(nome);
		txtEmail.sendKeys(email);
		
//		driver.findElement(By.id("cliente:btnSalvar")).click();
		txtCpf.submit();
	}
	
	public void edita(String CPF, String nome, String email) {
		driver.findElement(By.id("tabelaCliente:singleDT:0:btnSelecionar"));
		
		WebElement txtCPF = driver.findElement(By.id("cliente:cpf"));
		WebElement txtNome = driver.findElement(By.id("cliente:nome"));
		WebElement txtEmail = driver.findElement(By.id("cliente:email"));
		
		txtCPF.clear();
		txtCPF.sendKeys(CPF);
		txtNome.clear();
		txtNome.sendKeys(nome);
		txtEmail.clear();
		txtEmail.sendKeys(email);
		
		driver.findElement(By.id("cliente:btnSalvar")).click();
	}
	
	public void remove() {
		driver.findElement(By.id("tabelaCliente:singleDT:0:btnExcluir")).click();
		driver.findElement(By.id("btnSim")).click();
		
//		driver.findElement(By.id("form:singleDT:0:j_idt45")).click();
		// falta descobrir como pegar bot�o do alert ou tentar colocar o confirm do primefaces!
	}
	
}
