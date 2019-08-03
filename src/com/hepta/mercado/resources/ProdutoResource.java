package com.hepta.mercado.resources;

import java.net.URI;
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
import javax.ws.rs.FormParam;
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
import javax.ws.rs.core.UriInfo;

import com.hepta.mercado.domain.Produto;
import com.hepta.mercado.persistence.ProdutoDAO;
import com.hepta.mercado.services.ProdutoService;

@Path("/produtos")
public class ProdutoResource {
	
	@Context
	private HttpServletRequest request;

	@Context
	private HttpServletResponse response;
	
	UriInfo uriInfo;

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
	 * Pega um produto do mercado
	 * 
	 * @return response 200 (OK) - Conseguiu pegar
	 */
	@Path("produto/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Response getProduto(@PathParam("id") Integer id) {
		
		Produto produto;
		
		try {
			
			produto = service.find(id);
			
		} catch(Exception e) {
			
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao buscar produto").build();
		}
		
		GenericEntity<Produto> entity = new GenericEntity<Produto>(produto) {};
		
		return Response.status(Status.OK).entity(entity).build();
	}
	
	/**
	 * Lista todos os produtos do mercado
	 * 
	 * @return response 200 (OK) - Conseguiu listar
	 */
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Response getProdutos() {
		
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
	 * @throws Exception 
	 */
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	public Response produtoCreate(Produto produto) throws Exception {
		
		try {
			service.insert(produto);
			
		} catch (Exception e) {
			
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao inserir um produto").build();
		}
		
		
		
		//URI uri = uriInfo.getAbsolutePathBuilder().path(id).build();
		
		//return Response.created(uri).build();
		
		return Response.status(Status.CREATED).entity(produto).build();
		
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
		
		try {
			
			produto.setId(id);
			service.update(produto);
			
		} catch (Exception e) {
			
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao atualizar o produto").build();
		}
		
		return Response.noContent().build();
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
		
		try {
			service.delete(id);
			
		} catch (Exception e) {
			
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao deletar o produto").build();
		}
		
		return Response.noContent().build();
	}

}
