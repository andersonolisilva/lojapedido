package br.lojapedido.controle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class AbstractController<T> {

	protected T obj;
	
	public void addSuccess(String msg) {
		FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage(null, fm);
	}
	
	public void addInfo(String msg) {
		FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage(null, fm);
	}
	
	public void addErro(String msg) {
		FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage(null, fm);
	}
	
	public void addWarnin(String msg) {
		FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, msg, "");
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage(null, fm);
	}
	
	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}
}
