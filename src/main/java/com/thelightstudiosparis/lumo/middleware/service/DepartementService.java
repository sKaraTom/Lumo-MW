package com.thelightstudiosparis.lumo.middleware.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;

import com.thelightstudiosparis.lumo.middleware.dao.DaoException;
import com.thelightstudiosparis.lumo.middleware.dao.DepartementDao;
import com.thelightstudiosparis.lumo.middleware.objetmetier.departement.Departement;


@Stateless
@Transactional
public class DepartementService {

	@EJB
	DepartementDao departementDao;
	
	/**
	 * obtenir tous les départements de la table.
	 * 
	 * @return List<Departement>
	 * @throws DaoException si échec à l'obtention depuis la base de données.
	 */
	public List<Departement> obtenirTousDepartements() throws DaoException {
		
		List<Departement> listeDepartements = departementDao.obtenirTousDepartements();
		
		return listeDepartements;
	}
	
	
	
	
}
