package com.thelightstudiosparis.lumo.middleware.service;

import java.util.Calendar;
import java.util.Objects;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.internet.AddressException;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;

import com.thelightstudiosparis.lumo.middleware.dao.CompteDao;
import com.thelightstudiosparis.lumo.middleware.objetmetier.compte.Compte;
import com.thelightstudiosparis.lumo.middleware.objetmetier.compte.CompteExistantException;
import com.thelightstudiosparis.lumo.middleware.objetmetier.compte.CompteInvalideException;
import com.thelightstudiosparis.lumo.middleware.objetmetier.compte.EmailInvalideException;
import com.thelightstudiosparis.lumo.middleware.objetmetier.membre.Membre;
import com.thelightstudiosparis.lumo.middleware.objetmetier.membre.MembreInvalideException;


@Stateless
@Transactional
public class CompteService {
	
	
	@EJB
	private CompteDao compteDao;
	
	
	public void creerCompte(final Compte compte) throws CompteInvalideException, CompteExistantException, EmailInvalideException, MembreInvalideException {
		
		this.validerCompte(compte);
		this.validerMembre(compte.getMembre());
		
		// charger la date à l'instant de la création.
		compte.setDateDeCreation(Calendar.getInstance());
		
		compteDao.creerCompte(compte);
	}
	
	
	private void validerCompte(final Compte compte) throws CompteInvalideException, EmailInvalideException {
		
		if (Objects.isNull(compte))
			throw new CompteInvalideException("Le compte ne peut être null.");
		
		if (StringUtils.isBlank(compte.getPassword()))
			throw new CompteInvalideException("Le mot de passe du compte ne peut valoir null/blanc.");
		
		try {
			compte.getEmail().validate();
			
		} catch (AddressException emailInvalideException) {
			throw new EmailInvalideException(emailInvalideException);
		}
	}
	
	
	private void validerMembre(final Membre membre) throws MembreInvalideException {
		
		if (Objects.isNull(membre))
			throw new MembreInvalideException("Le membre ne peut être null");
		
		// validation de tous les champs à ajouter.
		
	}
	
	
	
}
