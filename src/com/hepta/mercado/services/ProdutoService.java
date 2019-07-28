package com.hepta.mercado.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.hepta.mercado.domain.Produto;
import com.hepta.mercado.persistence.ProdutoDAO;
	
@Stateless
public class ProdutoService {
	
//	@Inject
//	private ProdutoDAO dao;
	
	private ProdutoDAO dao;
	
	public ProdutoService() {
		
		dao = new ProdutoDAO();
	}
	
	public Produto find(Integer id) throws Exception {
		
		Produto produto = dao.find(id);
        
        return produto;

    }
	
	public void insert(Produto obj) throws Exception {

        obj.setId(null);

        dao.save(obj);
    }

    public void update(Produto obj) throws Exception {

        Produto newObj =  find(obj.getId());

        dao.update(newObj);
    }


    public void delete(Integer id) throws Exception {

        find(id);
        
        dao.delete(id);
        
    }

    public List<Produto> findAll() throws Exception {

        return dao.getAll();
    }

}
