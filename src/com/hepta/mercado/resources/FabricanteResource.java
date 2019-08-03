package com.hepta.mercado.resources;

import java.util.ArrayList;
import java.util.List;

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
import javax.ws.rs.core.UriInfo;

import com.hepta.mercado.domain.Fabricante;
import com.hepta.mercado.domain.Fabricante;
import com.hepta.mercado.services.FabricanteService;

@Path("/fabricantes")
public class FabricanteResource {
	
	@Context
	private HttpServletRequest request;

	@Context
	private HttpServletResponse response;
	
	UriInfo uriInfo;
	
	private FabricanteService service;
	
	public FabricanteResource() {
		this.service = new FabricanteService();
	}
	
	
	protected void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	/**
	 * Pega um fabricante do mercado
	 * 
	 * @return response 200 (OK) - Conseguiu pegar
	 */
	@Path("fabricante/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Response getFabricante(@PathParam("id") Integer id) {
		
		Fabricante fabricante;
		
		try {
			
			fabricante = service.find(id);
			
		} catch(Exception e) {
			
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao buscar fabricante").build();
		}
		
		GenericEntity<Fabricante> entity = new GenericEntity<Fabricante>(fabricante) {};
		
		return Response.status(Status.OK).entity(entity).build();
	}
	
	/**
	 * Lista todos os fabricantes do mercado
	 * 
	 * @return response 200 (OK) - Conseguiu listar
	 */
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Response getFabricante() {
		
		List<Fabricante> fabricantes = new ArrayList<>();
		
		try {
			
			fabricantes = service.findAll();
			
		} catch(Exception e) {
			
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao buscar fabricantes").build();
		}
		
		GenericEntity<List<Fabricante>> entity = new GenericEntity<List<Fabricante>>(fabricantes) {};
		
		return Response.status(Status.OK).entity(entity).build();
	}
	
	/**
	 * Adiciona novo fabricante no mercado
	 * 
	 * @param fabricante: Novo fabricante
	 * @return response 200 (OK) - Conseguiu adicionar
	 * @throws Exception 
	 */
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	public Response fabricanteCreate(Fabricante fabricante) throws Exception {
		
		try {
			service.insert(fabricante);
			
		} catch (Exception e) {
			
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao inserir um fabricante").build();
		}
		
		return Response.status(Status.CREATED).entity(fabricante).build();
		
	}
	
	
	
	/**
	 * Atualiza um fabricante no mercado
	 * 
	 * @param id: id do fabricante
	 * @param fabricante: Fabricante atualizado
	 * @return response 200 (OK) - Conseguiu atualiza
	 */
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@PUT
	public Response fabricanteUpdate(@PathParam("id") Integer id, Fabricante fabricante) {
		
		try {
			
			fabricante.setId(id);
			service.update(fabricante);
			
		} catch (Exception e) {
			
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao atualizar o fabricante").build();
		}
		
		return Response.noContent().build();
	}
	
	/**
	 * Remove um fabricante do mercado
	 * 
	 * @param id: id do fabricante
	 * @return response 200 (OK) - Conseguiu remover
	 */
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@DELETE
	public Response fabricanteDelete(@PathParam("id") Integer id) {
		
		try {
			service.delete(id);
			
		} catch (Exception e) {
			
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao deletar o fabricante").build();
		}
		
		return Response.noContent().build();
	}

}
