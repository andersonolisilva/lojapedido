package br.lojapedido.dao;

import java.util.List;

import javax.persistence.EntityManager;

public interface IGenericDAO<T extends PersistDB> {

	public T castGeneric(PersistDB obj);
	
	public abstract EntityManager getEm();

	public abstract void close();

	public abstract void create(T c);

	public abstract void update(T c);

	public abstract void delete(T c);

	public abstract T findByPrimaryKey(int id);

	public abstract List<T> findAll();

	public abstract List<T> findAllLike(String coluna, String valor);

	public abstract Class<T> getClassType();

}