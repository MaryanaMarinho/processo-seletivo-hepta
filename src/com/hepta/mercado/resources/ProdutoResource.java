package com.hepta.mercado.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.hepta.mercado.domain.Produto;
import com.hepta.mercado.persistence.ProdutoDAO;
import com.hepta.mercado.services.ProdutoService;

@Path("/produtos")
public class ProdutoResource {
	
	@Context
	private HttpServletRequest request;

	@Context
	private HttpServletResponse response;

//	private ProdutoDAO dao;
//	
//	public ProdutoResource() {
//		dao = new ProdutoDAO();
//	}
	
//	@Inject
//	private ProdutoService service;
	
	private ProdutoService service;
	
	public ProdutoResource() {
		service = new ProdutoService();
	}
	
	
	
	protected void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	/**
	 * Lista todos os produtos do mercado
	 * 
	 * @return response 200 (OK) - Conseguiu listar
	 */
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Response getProduto() {
		
		List<Produto> produtos = new ArrayList<>();
		
		try {
			
			produtos = service.findAll();
			
		} catch(Exception e) {
			
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao buscar produtos").build();
		}
		
		GenericEntity<List<Produto>> entity = new GenericEntity<List<Produto>>(produtos) {};
		
		return Response.status(Status.OK).entity(entity).build();
	}
	
	/**
	 * Adiciona novo produto no mercado
	 * 
	 * @param produto: Novo produto
	 * @return response 200 (OK) - Conseguiu adicionar
	 */
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	public Response produtoCreate(Produto produto) {
		return Response.status(Status.NOT_IMPLEMENTED).build();
	}
	
	
	
	/**
	 * Atualiza um produto no mercado
	 * 
	 * @param id: id do produto
	 * @param produto: Produto atualizado
	 * @return response 200 (OK) - Conseguiu atualiza
	 */
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@PUT
	public Response produtoUpdate(@PathParam("id") Integer id, Produto produto) {
		return Response.status(Status.NOT_IMPLEMENTED).build();
	}
	
	/**
	 * Remove um produto do mercado
	 * 
	 * @param id: id do produto
	 * @return response 200 (OK) - Conseguiu remover
	 */
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@DELETE
	public Response produtoDelete(@PathParam("id") Integer id) {
		return Response.status(Status.NOT_IMPLEMENTED).build();
	}

}
