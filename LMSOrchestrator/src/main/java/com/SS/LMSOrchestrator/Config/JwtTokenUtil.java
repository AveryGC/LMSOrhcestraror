package com.SS.LMSOrchestrator.Config;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5442306768470375097L;
	
	public static final long JWT_TOKEN_VALIDITY= 7*50*40*80000;
	
	@Value("${jwt.secret}")
	private String secret;
	
	//retireve username
	public String getUsernameFromTaken(String token) {
		return getClaimFromToken(token,Claims::getSubject);
	}
	
	//retrieve expiration date
	public Date getExpirationFromToken(String token) {
		return getClaimFromToken(token,Claims::getExpiration);
	}
	
	// gets all claims from token
	public <T> T getClaimFromToken(String token, Function <Claims, T>  claimsResolver) {
		Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	
	//extracts claims token using the secret key
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	private Boolean isTokenExpired(String token) {
		Date expiration = getExpirationFromToken(token);
		return expiration.before(new Date());
	}
	
	public String genrateToken(UserDetails userDetails) {
		return doGenerateToken(new HashMap<String,Object>(), userDetails.getUsername());
	}
	
	private String doGenerateToken(Map<String,Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY))
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}
	
	//validate token
	public Boolean validateToken(String token, UserDetails userDetials) {
		String username = getUsernameFromTaken(token);
		return (username.equals(userDetials.getUsername())&& !isTokenExpired(token));
	}
	

}
