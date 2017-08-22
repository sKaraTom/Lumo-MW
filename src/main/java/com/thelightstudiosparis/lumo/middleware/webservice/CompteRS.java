package com.thelightstudiosparis.lumo.middleware.webservice;

import javax.ejb.EJB;
import javax.jws.WebService;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thelightstudiosparis.lumo.middleware.dao.DaoException;
import com.thelightstudiosparis.lumo.middleware.objetmetier.compte.Compte;
import com.thelightstudiosparis.lumo.middleware.objetmetier.compte.CompteExistantException;
import com.thelightstudiosparis.lumo.middleware.objetmetier.compte.CompteInvalideException;
import com.thelightstudiosparis.lumo.middleware.objetmetier.compte.EmailInvalideException;
import com.thelightstudiosparis.lumo.middleware.objetmetier.departement.DepartementIntrouvableException;
import com.thelightstudiosparis.lumo.middleware.objetmetier.membre.MembreInvalideException;
import com.thelightstudiosparis.lumo.middleware.objetmetier.profession.ProfessionIntrouvableException;
import com.thelightstudiosparis.lumo.middleware.service.CompteService;


@WebService
@Transactional
@Path("/compte")
public class CompteRS {
	
	private static final Logger LOGGER =
			LoggerFactory.getLogger(CompteRS.class);
	
	
	@EJB
	private CompteService compteService;

	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces("text/plain")
	public Response creerCompte (Compte compte) {
		
		Response.ResponseBuilder builder = null;

		try {
			compteService.creerCompte(compte);
			builder = Response.ok("compte créé avec succès.");
			
		} catch (CompteInvalideException e) {
			builder = Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage());

		} catch (CompteExistantException e) {
			builder = Response.status(Response.Status.CONFLICT).entity(e.getMessage());

		} catch (EmailInvalideException e) {
			builder = Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage());

		} catch (MembreInvalideException e) {
			builder = Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage());
			
		} catch (DepartementIntrouvableException e) {
			builder = Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage());
		
		} catch (ProfessionIntrouvableException e) {
			builder = Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage());
			
		} catch (DaoException e) {
			builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage());
		}
		
        return builder.build();
	}
	
	
}
