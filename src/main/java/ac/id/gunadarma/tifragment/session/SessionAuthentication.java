package ac.id.gunadarma.tifragment.session;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class SessionAuthentication {
	
    public static Authentication getAuthenticationContext() {
    	return SecurityContextHolder.getContext().getAuthentication();
    }
    
    public static boolean hasRole(String role) {
    	Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    	return authorities.contains(new SimpleGrantedAuthority(role));
    }
}
