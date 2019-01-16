package com.hepta.mercado.rest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MercadoServiceTest {

	private static WebTarget service;
	private static final String URL_LOCAL = "http://localhost:8080/mercado/rs/produtos";
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		service = client.target( UriBuilder.fromUri(URL_LOCAL).build() );
	}

	@Test
	void testListaTodosProdutos() {
		// QUANDO
		Response response = service.request().get();
		// ENTAO
		assertTrue(response.getStatusInfo().getStatusCode() == Response.Status.OK.getStatusCode());
	}

}
