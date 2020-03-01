/**
 * 
 */
package com.SS.LMSOrchestrator.Service;

import java.lang.module.FindException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.SS.LMSOrchestrator.model.User;

/**
 * @author acorb
 *
 */
@Component
public class JwtUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = new User();
		user.setUsername(null);
		if(username.equals("admin")) {
			user.setUsername("admin");
			user.setPassword("admin");
			user.setRoles(new String[] {"ADMIN"} );
		}
		if(username.equals("john")) {
			user.setUsername("john");
			user.setPassword("fake");
			user.setRoles(new String[] {"BORROWER"} );
		}
		if(username.equals("nancy")) {
			user.setUsername("nancy");
			user.setPassword("nanny");
			user.setRoles(new String[] {"LIBRARIAN"} );
		}
		
		
		UserBuilder builder = null;
	    if (user.getUsername() != null) {
	      builder = org.springframework.security.core.userdetails.User.withUsername(username);
	      builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
//	      builder.password(user.getPassword());
	      builder.roles(user.getRoles());
	    } else {
	      throw new UsernameNotFoundException("User not found.");
	    }
	    
	    return builder.build();
	
	}
}
