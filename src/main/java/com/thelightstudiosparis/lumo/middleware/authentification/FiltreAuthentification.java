package com.thelightstudiosparis.lumo.middleware.authentification;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ejb.EJB;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 * méthode qui alloue à l'annotation @Authentifie la validation
 * d'un token passé dans le header d'une requête.
 *
 */
@Authentifie
@Provider
@Priority(Priorities.AUTHENTICATION)
public class FiltreAuthentification implements ContainerRequestFilter {
	
	@EJB
	JetonService jetonService;
	
	/**
	 * méthode implémentée de l'interface ContainerRequestFilter
	 * extraire le token, et vérifier qu'il est valide.
	 */
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		//récupérer header authorization de la requête HTTP
				String headerAuthorization = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
				
				 // vérifier que le header 'authorization' est bien présent et formaté.
		        if (headerAuthorization == null || !headerAuthorization.startsWith("Bearer ")) {
		        	 throw new NotAuthorizedException("header Authorization requis");
		        }	

		        // Extraire le token du header http
		        String token = headerAuthorization.substring("Bearer".length()).trim();
		        

		            // valider le token : séparer l'échec d'authentification de l'expiration du token.
		        	jetonService.validerToken(token);
		        	 
//		        } catch (Exception e) {
//		           // n'importe quelle exception annule la connexion côté ihm.
//		        	requestContext.abortWith(
//		                Response.status(Response.Status.UNAUTHORIZED).build());
//		        }
		
		
	}

}
