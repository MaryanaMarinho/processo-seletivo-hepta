package com.hepta.mercado.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.hepta.mercado.entity.Produto;

public class ProdutoDAO {

	public void save(Produto produto) throws Exception {
		EntityManager em = HibernateUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(produto);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e);
		} finally {
			em.close();
		}
	}

	public Produto update(Produto produto) throws Exception {
		EntityManager em = HibernateUtil.getEntityManager();
		Produto produtoAtualizado = null;
		try {
			em.getTransaction().begin();
			produtoAtualizado = em.merge(produto);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e);
		} finally {
			em.close();
		}
		return produtoAtualizado;
	}

	public void delete(Integer id) throws Exception {
		EntityManager em = HibernateUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			Produto produto = em.find(Produto.class, id);
			em.remove(produto);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e);
		} finally {
			em.close();
		}

	}

	public Produto find(Integer id) throws Exception {
		EntityManager em = HibernateUtil.getEntityManager();
		Produto produto = null;
		try {
			produto = em.find(Produto.class, id);
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e);
		} finally {
			em.close();
		}
		return produto;
	}

	@SuppressWarnings("unchecked")
	public List<Produto> getAll() throws Exception {
		EntityManager em = HibernateUtil.getEntityManager();
		List<Produto> produtos = new ArrayList<>();
		try {
			Query query = em.createQuery("FROM Produto");
			produtos = query.getResultList();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e);
		} finally {
			em.close();
		}
		return produtos;
	}
	
}
