package com.thelightstudiosparis.lumo.middleware.authentification;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.ejb.Stateless;
import javax.transaction.Transactional;

import com.thelightstudiosparis.lumo.middleware.objetmetier.membre.Membre;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Stateless
@Transactional
public class JetonService {
	
	private final String clef = "%^$lsf#&HTYasfgva584120";
	
	
	/**
	 * méthode de création d'un token JJWT.
	 * 
	 * @param compte pour extraire les informations à intégrer au token.
	 * @return String un token
	 * @throws UnsupportedEncodingException
	 */
	public String creerToken(Membre membre) throws UnsupportedEncodingException {
		
		Date date = new Date();
		long t = date.getTime();
		Date dateExpiration = new Date(t + (2400*60*60*1000)); // date de maintenant + 100 jours. 100*24h*60mn*60sec*1000ms
		
		// construction du token
		String token = Jwts.builder()
				  .setSubject(membre.getUuid().toString().substring(0, 15)) // je prends un extrait de l'uuid pour générer un sujet propre à l'utilisateur.
				  .setIssuedAt(date)
				  .setExpiration(dateExpiration)
				  .claim("prenom", membre.getPrenom())
				  .signWith(
				    SignatureAlgorithm.HS256,
				    clef.getBytes("UTF-8")
				  )
				  .compact();
		
		return token;	
	}
	
	/**
	 * méthode de validation d'un token.
	 * 
	 * @param token
	 * @throws ExpiredJwtException si la date d'expiration est passée.
	 * @throws UnsupportedJwtException
	 * @throws MalformedJwtException
	 * @throws SignatureException
	 * @throws IllegalArgumentException
	 * @throws UnsupportedEncodingException
	 */
	public void validerToken(String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException,
	SignatureException, IllegalArgumentException, UnsupportedEncodingException {
		
	 
		Jws<Claims> jws = Jwts.parser().setSigningKey(clef.getBytes("UTF-8")).parseClaimsJws(token);

	}
	
}
