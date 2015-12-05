package br.lojapedido.teste;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ClientesPage {

	private WebDriver driver;
	
	public ClientesPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void visita() {
		driver.get("http://localhost:8080/pedido/cliente.xhtml");
	}
	
	public boolean existeNaListagem(String CPF, String nome, String email) {
		return driver.getPageSource().contains(CPF) &&
				driver.getPageSource().contains(nome) &&
				driver.getPageSource().contains(email);
	}
	
	public void cadastra(String CPF, String nome, String email) {
		WebElement txtCPF = driver.findElement(By.name("cliente:cpf"));
		WebElement txtNome = driver.findElement(By.name("cliente:nome"));
		WebElement txtEmail = driver.findElement(By.name("cliente:email"));
		
		txtCPF.sendKeys(CPF);
		txtNome.sendKeys(nome);
		txtEmail.sendKeys(email);
		
		txtCPF.submit();
	}
	
}
