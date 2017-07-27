package com.thelightstudiosparis.lumo.middleware.objetmetier.profession;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "T_PROFESSION")
public class Profession implements Serializable {
	
	Integer id;
	String metier;
	
	List<Membre>listeMembres;

	public Profession() {
		super();
	}

	public Profession(Integer id, String metier, List<Membre> listeMembres) {
		super();
		this.id = id;
		this.metier = metier;
		listeMembres = listeMembres;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRO_ID")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "PRO_METIER")
	public String getMetier() {
		return metier;
	}

	public void setMetier(String metier) {
		this.metier = metier;
	}
	
	@ManyToMany
	@JoinTable(name="TJ_PRO_MEM")
	public List<Membre> getListeMembres() {
		return listeMembres;
	}

	public void setListeMembres(List<Membre> listeMembres) {
		listeMembres = listeMembres;
	}
	
	@Override
	public int hashCode() {
		
		return new HashCodeBuilder()
				.append(this.id)
				.append(this.metier)
				.build();
	}

	@Override
	public boolean equals(final Object candidat) {
		
		if (candidat == this)
			return true;
		
		if (candidat == null)
			return false;
		
		if (!(candidat instanceof Profession))
			return false;
		
		final Profession autre = (Profession) candidat; 
		
		return new EqualsBuilder()
				.append(this.id, autre.id)
				.append(this.metier, autre.metier)
				.build();
	}

	@Override
	public String toString() {
		
		return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
				.append("Identifiant", this.id)
				.append("Métier", this.metier)
				.append("Membres", this.listeMembres)
				.build();
	}
	
}
