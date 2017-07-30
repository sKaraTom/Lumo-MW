package com.thelightstudiosparis.lumo.middleware.objetmetier.membre;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.thelightstudiosparis.lumo.middleware.objetmetier.departement.Departement;
import com.thelightstudiosparis.lumo.middleware.objetmetier.profession.Profession;


@XmlRootElement
@Entity
@Table(name = "T_MEMBRE")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="uuid")
public class Membre implements Serializable {

	private UUID uuid;
	private String nom;
	private String prenom;
	
	private String urlSite;
	private String photo;
	
	private List<Profession> listeProfessions;
	private List<Departement> listeDepartements;
	
	private Integer compteurPopularite;
	private ArrayList<String> listeVotes;
	
	private ArrayList<Membre> listeFavoris;


	public Membre() {
		super();
	}
	
	public Membre(UUID uuid, String nom, String prenom, String urlSite, String photo, List<Profession> listeProfessions,
			List<Departement> listeDepartements, Integer compteurPopularite, ArrayList<String> listeVotes,
			ArrayList<Membre> listeFavoris) {
		super();
		this.uuid = uuid;
		this.nom = nom;
		this.prenom = prenom;
		this.urlSite = urlSite;
		this.photo = photo;
		this.listeProfessions = listeProfessions;
		this.listeDepartements = listeDepartements;
		this.compteurPopularite = compteurPopularite;
		this.listeVotes = listeVotes;
		this.listeFavoris = listeFavoris;
	}





	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name ="uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name="MEM_UUID")
	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	
	@Column(name = "MEM_NOM")
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	@Column(name = "MEM_PRENOM")
	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	@Column(name = "MEM_URL")
	public String getUrlSite() {
		return urlSite;
	}

	public void setUrlSite(String urlSite) {
		this.urlSite = urlSite;
	}
	
	@Column(name = "MEM_PHOTO")
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	@ManyToMany(mappedBy = "listeMembres", cascade={ CascadeType.MERGE })
	public List<Profession> getListeProfessions() {
		return listeProfessions;
	}

	public void setListeProfessions(List<Profession> listeProfessions) {
		this.listeProfessions = listeProfessions;
	}
	
	@ManyToMany (mappedBy = "listeMembres", cascade={ CascadeType.MERGE })
	public List<Departement> getListeDepartements() {
		return listeDepartements;
	}

	public void setListeDepartements(List<Departement> listeDepartements) {
		this.listeDepartements = listeDepartements;
	}
	
	@Column(name = "MEM_COMPTEURPOP")
	public Integer getCompteurPopularite() {
		return compteurPopularite;
	}

	public void setCompteurPopularite(Integer compteurPopularite) {
		this.compteurPopularite = compteurPopularite;
	}
	
	@Basic
	@Column(name = "MEM_VOTES")
	public ArrayList<String> getListeVotes() {
		return listeVotes;
	}

	public void setListeVotes(ArrayList<String> listeVotes) {
		this.listeVotes = listeVotes;
	}
	
//	@ElementCollection
//	@CollectionTable(name="Favoris", joinColumns=@JoinColumn(name="membre_uuid"))
	@Basic
	@Column(name="MEM_FAVORIS")
	public ArrayList<Membre> getListeFavoris() {
		return listeFavoris;
	}

	public void setListeFavoris(ArrayList<Membre> listeFavoris) {
		this.listeFavoris = listeFavoris;
	}

	@Override
	public int hashCode() {
		
		return new HashCodeBuilder()
				.append(this.nom)
				.append(this.prenom)
				.append(this.urlSite)
				.append(this.photo)
				.append(this.compteurPopularite)
				.build();
	}

	@Override
	public boolean equals(final Object candidat) {
		
		if (candidat == this)
			return true;
		
		if (candidat == null)
			return false;
		
		if (!(candidat instanceof Membre))
			return false;
		
		final Membre autre = (Membre) candidat; 
		
		return new EqualsBuilder()
				.append(this.nom,autre.nom)
				.append(this.prenom,autre.prenom)
				.append(this.urlSite,autre.urlSite)
				.append(this.photo, autre.photo)
				.append(this.compteurPopularite, autre.compteurPopularite)
				.build();
	}

	@Override
	public String toString() {
		
		return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
				.append("UUID", this.uuid)
				.append("Nom", this.nom)
				.append("Prenom", this.prenom)
				.append("Url", this.urlSite)
				.append("Photo", this.photo)
				.append("Professions", this.listeProfessions)
				.append("Départements", this.listeDepartements)
				.append("Popularite", this.compteurPopularite)
				.append("Votes", this.listeVotes)
				.append("Favoris", this.listeFavoris)
				.build();
	}
	
	
}
