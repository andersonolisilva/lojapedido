package br.lojapedido.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.lojapedido.dominio.Produto;

@FacesConverter("produtoSelectOneMenuConverter")
public class ProdutoSelectOneMenuConverter implements Converter {

	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if (value != null && value.trim().length() > 0) {
			try {
				FacesContext context = FacesContext.getCurrentInstance();
				ProdutoSelectOneMenu produto = (ProdutoSelectOneMenu) context.getELContext().getELResolver().getValue(context.getELContext(), null, "produtoSelectOneMenu");
		        return (Produto)produto.findByProdutoById( Integer.parseInt(value) );
			} catch (NumberFormatException e) {
				throw new ConverterException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Erro na Conversão",
						"Não foi possível obter a lista de produtos."));
			}
		} else {
			return null;
		}
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if (object != null) {
			return String.valueOf(((Produto) object).getId());
		} else {
			return null;
		}
	}
}