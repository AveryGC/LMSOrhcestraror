/**
 * 
 */
package com.SS.LMSOrchestrator.model;

import java.io.Serializable;

/**
 * @author acorb
 *
 */
public class JwtRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7811036747321634921L;
	
	private String username;
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
