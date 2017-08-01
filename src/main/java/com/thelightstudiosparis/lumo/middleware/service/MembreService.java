package com.thelightstudiosparis.lumo.middleware.service;

import java.util.List;
import java.util.UUID;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;

import com.thelightstudiosparis.lumo.middleware.dao.MembreDao;
import com.thelightstudiosparis.lumo.middleware.objetmetier.departement.Departement;
import com.thelightstudiosparis.lumo.middleware.objetmetier.membre.Membre;
import com.thelightstudiosparis.lumo.middleware.objetmetier.membre.MembreIntrouvableException;
import com.thelightstudiosparis.lumo.middleware.objetmetier.profession.Profession;

@Stateless
@Transactional
public class MembreService {
	
	
	@EJB
	MembreDao membreDao;
	
	/**
	 * Obtenir un membre à partir de son UUID
	 * 
	 * @param uuid
	 * @return
	 * @throws MembreIntrouvableException
	 */
	public Membre obtenirMembre(UUID uuid) throws MembreIntrouvableException {
		
		return membreDao.obtenirMembre(uuid);
	}
	
	/**
	 * obtenir une liste de membres depuis une recherche
	 * faite à partir d'un département et d'une profession.
	 * 
	 * @param numeroDepartement
	 * @param idProfession
	 * @param uuidMembre
	 * @return List<Membre> les membres répondant aux critères de la recherche.
	 */
	public List<Membre> chercherMembres(String numeroDepartement, Integer idProfession, UUID uuidMembre) {
		
		List<Membre> resultatsRecherche = membreDao.chercherMembres(numeroDepartement, idProfession, uuidMembre);
		
		return resultatsRecherche;
		
		
	}
	
	
	
}
