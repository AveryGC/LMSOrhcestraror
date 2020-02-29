/**
 * 
 */
package com.SS.LMSOrchestrator.model;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

/**
 * @author acorb
 *
 */
public class JwtResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8471454283660449804L;
	@NotEmpty
	private final String jwtToken;

	/**
	 * @param jwtToken
	 */
	public JwtResponse(String jwtToken) {
		this.jwtToken = jwtToken;
	}


	public String getJwtToken() {
		return jwtToken;
	}
	
	
}
