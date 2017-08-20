package com.thelightstudiosparis.lumo.middleware.webservice;

import java.util.List;
import java.util.UUID;

import javax.ejb.EJB;
import javax.jws.WebService;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSON.Mode;

import com.thelightstudiosparis.lumo.middleware.dao.DaoException;
import com.thelightstudiosparis.lumo.middleware.dao.MembreDao;
import com.thelightstudiosparis.lumo.middleware.objetmetier.departement.Departement;
import com.thelightstudiosparis.lumo.middleware.objetmetier.membre.Membre;
import com.thelightstudiosparis.lumo.middleware.objetmetier.membre.MembreIntrouvableException;
import com.thelightstudiosparis.lumo.middleware.objetmetier.profession.Profession;
import com.thelightstudiosparis.lumo.middleware.service.MembreService;


@WebService
@Transactional
@Path("/membre")
public class MembreRS {
	
	@EJB
	MembreService membreService;
	
	private static final Logger LOGGER =
			LoggerFactory.getLogger(MembreRS.class);
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{uuid}")
	public Response obtenirMembre(@PathParam("uuid") UUID uuid) {
		
		Response.ResponseBuilder builder = null;
		
		Membre membre;
		
		try {
			membre = membreService.obtenirMembre(uuid);
			builder = Response.ok(membre);
		} catch (MembreIntrouvableException e) {
			builder = Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage());
		}
		
		return builder.build();
		
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{departement}/{profession}/{uuid}")
	public Response chercherMembres(
			@PathParam("departement") String numeroDepartement,
			@PathParam("profession")Integer idProfession,
			@PathParam("uuid") UUID uuidMembre) {
		
		Response.ResponseBuilder builder = null;
		
		List<Membre> listeMembres;
		
		listeMembres = membreService.chercherMembres(numeroDepartement, idProfession, uuidMembre);
		
		if(listeMembres.isEmpty() ){
			builder = Response.status(Response.Status.NO_CONTENT);
		}
		
		else {	
			// utilisation de la bibliothèque JSONIC
			// pour transformer en json la liste de membres
			// et mieux maîtriser les sous-niveaux de la liste (liste de membres contenant une liste de départements et professions).
			final JSON json = new JSON(4);
			json.setPrettyPrint(true);
			json.setMode(Mode.STRICT);
			
			final String listeMembresJson = json.format(listeMembres);
			
			builder = Response.ok(listeMembresJson);
		}
		
        return builder.build();
	}
	
}
