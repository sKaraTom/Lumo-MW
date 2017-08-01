package com.thelightstudiosparis.lumo.middleware.dao;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thelightstudiosparis.lumo.middleware.objetmetier.departement.Departement;
import com.thelightstudiosparis.lumo.middleware.objetmetier.membre.Membre;
import com.thelightstudiosparis.lumo.middleware.objetmetier.membre.MembreIntrouvableException;
import com.thelightstudiosparis.lumo.middleware.objetmetier.profession.Profession;

@Stateless
@Transactional
public class MembreDao {

	@PersistenceContext
	private EntityManager em;
	
	private static final Logger LOGGER =
			LoggerFactory.getLogger(MembreDao.class);
	
	/**
	 * obtenir un membre depuis son UUID
	 * 
	 * @param uuid
	 * @return
	 * @throws MembreIntrouvableException si la base de donnée ne renvoie pas de membre.
	 */
	public Membre obtenirMembre(UUID uuid) throws MembreIntrouvableException {
		
		Membre membre = em.find(Membre.class, uuid);
		
		if(Objects.isNull(membre))
			throw new MembreIntrouvableException("Aucun membre trouvé à partir de cet id.");
		
		return membre;
		
	}
	
	public List<Membre> chercherMembres(String numeroDepartement, Integer idProfession, UUID uuidMembre) {
		
		final StringBuilder buildRequete = new StringBuilder();
		
		buildRequete.append("SELECT m FROM Membre m");
		buildRequete.append(" JOIN m.listeProfessions lp ON lp.id=:profession");
		buildRequete.append(" JOIN m.listeDepartements ld ON ld.numero=:departement");
		buildRequete.append(" WHERE m.uuid !=:uuid");
		
		final TypedQuery<Membre> requete = em.createQuery(buildRequete.toString(),Membre.class);
		
		requete.setParameter("profession", idProfession);
		requete.setParameter("departement", numeroDepartement);
		requete.setParameter("uuid", uuidMembre);
		
		
		List<Membre> listeResultats = requete.getResultList();
		
		 return listeResultats;
	}
	
}
