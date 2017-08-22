package com.thelightstudiosparis.lumo.middleware.authentification;

import java.io.Serializable;
import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * l'objet Jeton envoyé côté ihm à l'authentification.
 * contient l'uuid du membre, son prénom et un token JJWT. 
 *
 */
@XmlRootElement
public class Jeton implements Serializable {
	
	private UUID uuidMembre;
	
	private String prenom;
	
	private String token;

	
	public Jeton() {
		super();
	}

	public Jeton(UUID uuidMembre, String prenom, String token) {
		super();
		this.uuidMembre = uuidMembre;
		this.prenom = prenom;
		this.token = token;
	}

	public UUID getUuidMembre() {
		return uuidMembre;
	}

	public void setUuidMembre(UUID uuidMembre) {
		this.uuidMembre = uuidMembre;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	@Override
	public int hashCode() {
		
		return new HashCodeBuilder()
				.append(this.uuidMembre)
				.append(this.prenom)
				.append(this.token)
				.build();
	}

	@Override
	public boolean equals(final Object candidat) {
		
		if (candidat == this)
			return true;
		
		if (candidat == null)
			return false;
		
		if (!(candidat instanceof Jeton))
			return false;
		
		final Jeton autre = (Jeton) candidat; 
		
		return new EqualsBuilder()
				.append(this.uuidMembre, autre.uuidMembre)
				.append(this.prenom, autre.prenom)
				.append(this.token, autre.token)
				.build();
	}

	@Override
	public String toString() {
		
		return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
				.append("Uuid membre", this.uuidMembre)
				.append("Prenom", this.prenom)
				.append("Token", this.token)
				.build();
	}	
	
	
	
}
