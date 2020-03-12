/**
 * 
 */
package com.SS.LMSOrchestrator.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.SS.LMSOrchestrator.Service.JwtUserDetailsService;

/**
 * @author acorb
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private JwtAuthenticationEntryPoint entryPoint;
	

	@Autowired
	private JwtUserDetailsService jwtUserDetailService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	//configures authentication manager, 
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(jwtUserDetailService).passwordEncoder(passwordEncoder())
			.and()
			.inMemoryAuthentication()
			.withUser("admin")
			.password("admin")
			.roles("ADMIN")
			.and().withUser("john")
			.password("fake")
			.roles("BORROWER")
			.and().withUser("nancy")
			.password("nancy")
			.roles("LIBRARIAN");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();	
	}
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//permits access to /authenticate to everyone
		http.csrf().disable().
		authorizeRequests().antMatchers("/lmsmain/authenticate").permitAll()
		//sets up authentcation for our micro services
		.antMatchers("/lmsmain/admin/*").hasRole("ADMIN")
		.antMatchers("/lmsmain/borrower/*").hasAnyRole("ADMIN","BORROWER","LIBRARIAN")
		.antMatchers("/lmsmain/librarian/*").hasAnyRole("ADMIN","LIBRARIAN")
		.anyRequest().authenticated().and()
		//sets up error to entrypoint
		.exceptionHandling().authenticationEntryPoint(entryPoint)
		//makes sension stateless
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		//add filter to validate the tokens with every request
		.and().addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/lmsmain/authenticate");
	}
	
	
}
