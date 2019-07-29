package com.hepta.mercado.services;

import java.util.List;

import javax.ejb.Stateless;

import com.hepta.mercado.domain.Fabricante;
import com.hepta.mercado.persistence.FabricanteDAO;
	
@Stateless
public class FabricanteService {
	
	private FabricanteDAO dao;
	
	public FabricanteService() {
		
		dao = new FabricanteDAO();
	}
	
	public Fabricante find(Integer id) throws Exception {
		
		Fabricante fabricante = dao.find(id);
        
        return fabricante;

    }
	
	public void insert(Fabricante obj) throws Exception {

        obj.setId(null);

        dao.save(obj);
        
    }

    public void update(Fabricante obj) throws Exception {

        Fabricante newObj =  find(obj.getId());
        updateData(newObj, obj);

        dao.update(newObj);
    }


    public void delete(Integer id) throws Exception {

        find(id);
        
        dao.delete(id);
        
    }

    public List<Fabricante> findAll() throws Exception {

        return dao.getAll();
    }
    
    private void updateData(Fabricante newObj, Fabricante obj) {
        newObj.setNome(obj.getNome());
    }

}
