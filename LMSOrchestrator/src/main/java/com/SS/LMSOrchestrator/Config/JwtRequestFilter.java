/**
 * 
 */
package com.SS.LMSOrchestrator.Config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.SS.LMSOrchestrator.Service.JwtUserDetailsService;

import io.jsonwebtoken.ExpiredJwtException;

/**
 * @author acorb
 *
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtUserDetailsService userDeatialsService;
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@Bean
	public FilterRegistrationBean jwtRequestFilterRegistration(JwtRequestFilter filter) {
	    FilterRegistrationBean registration = new FilterRegistrationBean(filter);
	    registration.setEnabled(false);
	    return registration;
	}
	
	@Override
	//get token and from header
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;
		
		if(requestTokenHeader !=null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username =jwtTokenUtil.getUsernameFromTaken(jwtToken);
			}catch(IllegalArgumentException e) {
				logger.warn("Unable to get JWT Token");
			}catch(ExpiredJwtException e) {
				logger.warn("JWT Token has expired");
			}
		}else {
			logger.warn("JWT Token does not begin with bearerString");
		}
		
		//validate token
		if(username != null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails = this.userDeatialsService.loadUserByUsername(username);
			
			if(jwtTokenUtil.validateToken(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken 
					= new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
			filterChain.doFilter(request, response);
		}
	}

}
