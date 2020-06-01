package auth.restcontroller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import auth.filter.JwtRequestFilter;
import auth.model.AuthenticationRequest;
import auth.model.AuthenticationResponse;
import auth.service.JwtUserDetailsService;
import auth.util.JwtTokenUtil;
import io.jsonwebtoken.Jwt;

@RestController
public class AuthRestController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@RequestMapping(value = "JwtUserDetailsService/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		
		//authentifie l'utilisateur
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		//charge l'utilisateur correspondant en objet UserDetails
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		
		//génère le JWT
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		
		//renvoie le JWT dans la réponse
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	
	/*
	@RequestMapping(value = "JwtUserDetailsService/validate", method = RequestMethod.POST)
	public boolean validateToken (@RequestBody String jwt) {
		
	}*/

}
