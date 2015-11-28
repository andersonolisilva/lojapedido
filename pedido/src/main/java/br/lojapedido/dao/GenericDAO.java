package br.lojapedido.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public abstract class GenericDAO <T extends PersistDB> implements IGenericDAO<T>{
		
	protected static EntityManager em;
		
	@SuppressWarnings("unchecked")
	public T castGeneric(PersistDB obj){
		return (T) obj;
	}
	
	private void change(T c, OperacaoDatabase op){
		EntityManager em = getEm();
		em.getTransaction().begin();
		switch (op) {
			case INSERIR:
				em.persist(c);
				break;
			case ALTERAR:
				em.merge(c);
				break;
			case REMOVER:
				em.remove(c);
				break;
		}
		em.getTransaction().commit();
		
	}

	/* (non-Javadoc)
	 * @see br.edu.unirn.dao.IGenericDAO#getEm()
	 */
	public EntityManager getEm() {
		
		if (em == null){
			em = Database.getInstance().getEntityManager();
		}else if (!em.isOpen()){
			em = Database.getInstance().getEntityManager();
		}
		
		return em;
	}
	
	/* (non-Javadoc)
	 * @see br.edu.unirn.dao.IGenericDAO#close()
	 */
	public void close(){
		
	}
	
	
	/* (non-Javadoc)
	 * @see br.edu.unirn.dao.IGenericDAO#create(T)
	 */
	public void create(T c){
		change(c, OperacaoDatabase.INSERIR);
	}
	/* (non-Javadoc)
	 * @see br.edu.unirn.dao.IGenericDAO#update(T)
	 */
	public void update(T c){
		change(c, OperacaoDatabase.ALTERAR);
	}
	/* (non-Javadoc)
	 * @see br.edu.unirn.dao.IGenericDAO#delete(T)
	 */
	public void delete(T c){
		change(c, OperacaoDatabase.REMOVER);
	}
	
	/* (non-Javadoc)
	 * @see br.edu.unirn.dao.IGenericDAO#findByPrimaryKey(int)
	 */
	public T findByPrimaryKey(int id){
		EntityManager em = getEm();
		T c = em.find(getClassType(), id);
		return c;
	}
	
	/* (non-Javadoc)
	 * @see br.edu.unirn.dao.IGenericDAO#findAll()
	 */
	public List<T> findAll(){
		EntityManager em = getEm();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(getClassType());
		TypedQuery<T> typedQuery = em.createQuery(query.select(query.from(getClassType())));
		List<T> c = typedQuery.getResultList();
		return c;
	}
	
	/* (non-Javadoc)
	 * @see br.edu.unirn.dao.IGenericDAO#findAllLike(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAllLike(String coluna,String valor){
		String tabela = getClassType().getSimpleName();
		String jpql = "from "+tabela+ " where "+coluna+" like :valor";
		EntityManager em = getEm();
		Query q = em.createQuery(jpql);
		q.setParameter("valor", "%"+valor+"%");
		List<T> retorno = q.getResultList();
		return retorno;
	}
		
	/* (non-Javadoc)
	 * @see br.edu.unirn.dao.IGenericDAO#getClassType()
	 */
	public abstract Class<T> getClassType();
}

enum OperacaoDatabase {
	INSERIR,ALTERAR,REMOVER;
}