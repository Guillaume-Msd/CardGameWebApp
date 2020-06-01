package auth.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	//charge l'utilisateur grâce au nom d'utilisateur (exemple avec un simple utilisateur)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			return new User("foo", "foo",new ArrayList<>());
	}
	
	
}