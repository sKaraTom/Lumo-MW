package com.thelightstudiosparis.lumo.middleware.dao;

import javax.ejb.Stateless;
import javax.mail.internet.InternetAddress;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thelightstudiosparis.lumo.middleware.objetmetier.compte.Compte;
import com.thelightstudiosparis.lumo.middleware.objetmetier.compte.CompteExistantException;
import com.thelightstudiosparis.lumo.middleware.objetmetier.compte.CompteInvalideException;



@Stateless
@Transactional
public class CompteDao {
	
	private static final Logger LOGGER =
			LoggerFactory.getLogger(CompteDao.class);
	
	
	@PersistenceContext
	private EntityManager em;
	
	
	/**
	 * ajouter un compte à la bdd
	 * 
	 * @param compte
	 * @throws CompteInvalideException si le compte à créer n'est pas valide.
	 * @throws CompteExistantException si un compte identique existe dans la bdd.
	 */
	public void creerCompte(final Compte compte) throws CompteInvalideException, CompteExistantException {
		
		try {
			em.persist(compte);
		}
		catch(final EntityExistsException e) {
			throw new CompteExistantException();
		}
		catch(final IllegalArgumentException e ) {
			throw new CompteInvalideException();
		}
	}
	
	
	

	/**
	 * vérifier qu'un compte n'existe pas avec le même email.
	 * 
	 * @param compteAVerifier
	 * @return
	 */
	public Boolean contenirCompte(final Compte compteAVerifier) {
		
		final String requeteJPQL = "SELECT c.email FROM Compte c WHERE c.email=:email";
		final Query requete = em.createQuery(requeteJPQL);
		requete.setParameter("email", compteAVerifier.getEmail());
		
		try {
			String resultat = (String) requete.getSingleResult();
			return true;
		}
		
		catch(NoResultException e) {
			return false;
		}
		
	}



}
