package br.lojapedido.servico;

import java.util.List;

public interface ICRUD<T> {

	public abstract void create();

	public abstract void update();

	public abstract void delete();

	public abstract T findByPrimaryKey(int id);

	public abstract List<T> findAll();

}
