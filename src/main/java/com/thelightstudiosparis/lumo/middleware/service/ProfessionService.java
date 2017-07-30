package com.thelightstudiosparis.lumo.middleware.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;

import com.thelightstudiosparis.lumo.middleware.dao.DaoException;
import com.thelightstudiosparis.lumo.middleware.dao.ProfessionDao;
import com.thelightstudiosparis.lumo.middleware.objetmetier.profession.Profession;

@Stateless
@Transactional
public class ProfessionService {

	@EJB
	ProfessionDao professionDao;
	
	/**
	 * obtenir toutes les professions depuis la table.
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List<Profession> obtenirToutesProfessions() throws DaoException {
		
		List<Profession> listeProfessions = professionDao.obtenirToutesProfessions();
		
		return listeProfessions;
	}
	
	
	
	
}
