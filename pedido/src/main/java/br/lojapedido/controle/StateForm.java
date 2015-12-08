package br.lojapedido.controle;

public class StateForm {

	private boolean registroNovo;	
	private boolean registroEdicao;
	private boolean registroLista;
	
	public StateForm(){
		this.controleForm("listar");
	}
	
	public void controleForm(String situacao){
		this.registroNovo = situacao.equals("adicionar");
		this.registroEdicao = situacao.equals("editar");
		this.registroLista = situacao.equals("listar");
	}

	public boolean isRegistroNovo() {
		return registroNovo;
	}

	public boolean isRegistroEdicao() {
		return registroEdicao;
	}

	public boolean isRegistroLista() {
		return registroLista;
	}
	
	
}
