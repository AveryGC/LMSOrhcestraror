/**
 * 
 */
package com.SS.LMSOrchestrator.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SS.LMSOrchestrator.Config.JwtTokenUtil;
import com.SS.LMSOrchestrator.model.JwtRequest;
import com.SS.LMSOrchestrator.model.JwtResponse;

/**
 * @author acorb
 *
 */
@RestController
@CrossOrigin
public class JwtAuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired 
	private UserDetailsService userDetailService;
	
	@PostMapping(value = "/authenticate")
	public ResponseEntity<?>  creatAuthenticationToken(@RequestBody @Valid JwtRequest request) throws Exception{

		final UserDetails userDetails = userDetailService.loadUserByUsername(request.getUsername());
		authenticate(request.getUsername(), request.getPassword());
		final String token = jwtTokenUtil.genrateToken(userDetails);
		
		return ResponseEntity.ok(new JwtResponse(token));
		
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
		}catch(DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		}catch(BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS",e);
		}
	}

}
