package br.pedido.arquitetura;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class ArquiteturaTest {

	public void clicarEmBotao(WebDriver driver, String nomeDoBotao) {
		
		WebElement btn = driver.findElement(By.name(nomeDoBotao));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", btn);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
