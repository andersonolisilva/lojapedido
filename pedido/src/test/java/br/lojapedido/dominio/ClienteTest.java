package br.lojapedido.dominio;

import static org.junit.Assert.*;

import org.junit.Test;

public class ClienteTest {

	@Test
	public void devePermitirCpfApenasCom11Digitos(){
		
		Cliente cliente = new Cliente("Anderson", "09098789876", "andersonolisilva@gmail.com");
		assertTrue(cliente.getCPF().length()==11);
	}
	
	
}
