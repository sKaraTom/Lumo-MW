package com.thelightstudiosparis.lumo.middleware.dao;

import java.util.List;
import java.util.Objects;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.thelightstudiosparis.lumo.middleware.objetmetier.profession.Profession;
import com.thelightstudiosparis.lumo.middleware.objetmetier.profession.ProfessionIntrouvableException;

@Stateless
@Transactional
public class ProfessionDao {

	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * obtenir une Profession
	 * 
	 * @param id
	 * @return la profession obtenue par son id.
	 * @throws ProfessionIntrouvableException
	 */
	public Profession obtenirProfession (Integer id) throws ProfessionIntrouvableException {
		
		Profession profession = em.find(Profession.class, id);
		
		if (Objects.isNull(profession))
			throw new ProfessionIntrouvableException("aucune profession trouvée à cet id.");
		
		return profession;
	}
	
	/**
	 * obtenir tous les tuples de la table Profession.
	 * 
	 * @return List<Profession>
	 * @throws DaoException si échec de communication avec la bdd.
	 */
	public List<Profession> obtenirToutesProfessions() throws DaoException {
		
		final String requeteJPQL = "Profession.obtenirToutesProfessions";
		 
		final Query requete = em.createNamedQuery(requeteJPQL);
		 
		List<Profession> listeProfessions;
		 
		try {
			 listeProfessions = requete.getResultList();
		} 
		catch(Exception e) {
				throw new DaoException("échec à l'obtention des professions depuis la base de données.");
		}
		
		return listeProfessions;
	}
	
	
	
	
}
