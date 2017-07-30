package com.thelightstudiosparis.lumo.middleware.webservice;

import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebService;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thelightstudiosparis.lumo.middleware.dao.DaoException;
import com.thelightstudiosparis.lumo.middleware.objetmetier.profession.Profession;
import com.thelightstudiosparis.lumo.middleware.service.ProfessionService;

@WebService
@Transactional
@Path("/profession")
public class ProfessionRS {

	
	@EJB
	ProfessionService professionService;
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenirToutesProfessions() {
	
		Response.ResponseBuilder builder = null;
		
		List<Profession> listeProfessions;
		try {
			listeProfessions = professionService.obtenirToutesProfessions();
			builder = Response.ok(listeProfessions);
		} catch (DaoException e) {
			builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage());
		}
		return builder.build();
	}
		
}
