package com.thelightstudiosparis.lumo.middleware.webservice;

import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebService;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thelightstudiosparis.lumo.middleware.dao.DaoException;
import com.thelightstudiosparis.lumo.middleware.objetmetier.departement.Departement;
import com.thelightstudiosparis.lumo.middleware.service.DepartementService;

@WebService
@Transactional
@Path("/departement")
public class DepartementRS {

	@EJB
	DepartementService departementService;
	
	private static final Logger LOGGER =
			LoggerFactory.getLogger(DepartementRS.class);
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenirTousDepartements() {
		
		Response.ResponseBuilder builder = null;
		
		try {
			List<Departement> listeDepartements = departementService.obtenirTousDepartements();
			builder = Response.ok(listeDepartements);
		} catch (DaoException e) {
			builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage());
		}
        return builder.build();
	}
	
	
	
	
	
		
}
