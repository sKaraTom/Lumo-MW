package com.thelightstudiosparis.lumo.middleware.objetmetier.compte;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.thelightstudiosparis.lumo.middleware.objetmetier.membre.Membre;

@XmlRootElement
@Entity
@Table(name = "T_COMPTE")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Compte  implements Serializable {

	
	private Integer id;
	
	private String email;
	
	private String password;
	
	private Calendar dateDeCreation;
	
	private Membre membre;

	public Compte() {
		super();
	}

	public Compte(Integer id, String email, String password, Calendar dateDeCreation, Membre membre) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.dateDeCreation = dateDeCreation;
		this.membre = membre;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COM_ID")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "COM_EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "COM_PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "COM_DATECREATION")
	public Calendar getDateDeCreation() {
		return dateDeCreation;
	}

	public void setDateDeCreation(Calendar dateDeCreation) {
		this.dateDeCreation = dateDeCreation;
	}
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="MEM_UUID")
	public Membre getMembre() {
		return membre;
	}

	public void setMembre(Membre membre) {
		this.membre = membre;
	}
	
	
	@Override
	public int hashCode() {
		
		return new HashCodeBuilder()
				.append(this.email)
				.append(this.password)
				.append(this.dateDeCreation)
				.build();
	}

	@Override
	public boolean equals(final Object candidat) {
		
		if (candidat == this)
			return true;
		
		if (candidat == null)
			return false;
		
		if (!(candidat instanceof Compte))
			return false;
		
		final Compte autre = (Compte) candidat; 
		
		return new EqualsBuilder()
				.append(this.email,autre.email)
				.append(this.password,autre.password)
				.append(this.dateDeCreation,autre.dateDeCreation)
				.build();
	}

	@Override
	public String toString() {
		
		return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
				.append("Identifiant :", this.id)
				.append("email", this.email)
				.append("Password", this.password)
				.append("Date de création", this.dateDeCreation)
				.append("Membre", this.membre)
				.build();
	}
	
	
	
}
