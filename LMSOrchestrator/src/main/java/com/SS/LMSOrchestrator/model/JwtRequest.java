/**
 * 
 */
package com.SS.LMSOrchestrator.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

/**
 * @author acorb
 *
 */
public class JwtRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7811036747321634921L;
	
	@NotBlank
	@Length(min = 3)
	private String username;
	@NotBlank
	@Length(min = 3)
	private String password;
	
	//For serialization
	public JwtRequest() {}
	
	/**
	 * @param username
	 * @param password
	 */
	public JwtRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
