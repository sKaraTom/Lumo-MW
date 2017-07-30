package com.thelightstudiosparis.lumo.middleware.dao;

import java.util.List;
import java.util.Objects;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.thelightstudiosparis.lumo.middleware.objetmetier.departement.Departement;
import com.thelightstudiosparis.lumo.middleware.objetmetier.departement.DepartementIntrouvableException;

@Stateless
@Transactional
public class DepartementDao {
	
	@PersistenceContext
	private EntityManager em;
	
	
	/**
	 * obtenir un département
	 * 
	 * @param numero String
	 * @return le département
	 * @throws DepartementIntrouvableException si aucun département trouvé au numéro entré en param
	 */
	public Departement obtenirDepartement(String numero) throws DepartementIntrouvableException {
		
		Departement departement = em.find(Departement.class, numero);
		
		if (Objects.isNull(departement))
			throw new DepartementIntrouvableException("aucun département trouvé à ce numéro.");
		
		return departement;
		
	}
	
	/**
	 * obtenir tous les départements de la table.
	 * 
	 * @return List<Departement>
	 * @throws DaoException 
	 */
	public List<Departement> obtenirTousDepartements() throws DaoException {
		
	 final String requeteJPQL = "Departement.obtenirTousDepartements";
	 
	 final Query requete = em.createNamedQuery(requeteJPQL);
	 
	 List<Departement> listeDepartements;
	 
	try {
		listeDepartements = requete.getResultList();
	} 
	catch(Exception e) {
		throw new DaoException("échec à l'obtention des départements depuis la base de données.");
	}
	 
	return listeDepartements;
	 
	}
	
	
	
	
	
}
