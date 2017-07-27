package com.thelightstudiosparis.lumo.middleware.objetmetier.departement;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.thelightstudiosparis.lumo.middleware.objetmetier.membre.Membre;

@XmlRootElement
@Entity
@Table(name = "T_DEPARTEMENT")
public class Departement implements Serializable {
		
	private Integer numero;
	private String nom;
	
	private List<Membre> listeMembres;

	public Departement() {
		super();
	}

	public Departement(Integer numero, String nom, List<Membre> listeMembres) {
		super();
		this.numero = numero;
		this.nom = nom;
		this.listeMembres = listeMembres;
	}
	
	@Id
	@Column(name = "DEP_ID")
	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
	@Column(name = "DEP_NOM")
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	@ManyToMany
	@JoinTable(name="TJ_DEP_MEM")
	public List<Membre> getListeMembres() {
		return listeMembres;
	}

	public void setListeMembres(List<Membre> listeMembres) {
		this.listeMembres = listeMembres;
	}
	
	@Override
	public int hashCode() {
		
		return new HashCodeBuilder()
				.append(this.numero)
				.append(this.nom)
				.build();
	}

	@Override
	public boolean equals(final Object candidat) {
		
		if (candidat == this)
			return true;
		
		if (candidat == null)
			return false;
		
		if (!(candidat instanceof Departement))
			return false;
		
		final Departement autre = (Departement) candidat; 
		
		return new EqualsBuilder()
				.append(this.numero, autre.numero)
				.append(this.nom, autre.nom)
				.build();
	}

	@Override
	public String toString() {
		
		return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
				.append("Numéro", this.numero)
				.append("Nom", this.nom)
				.append("Membres", this.listeMembres)
				.build();
	}
}
