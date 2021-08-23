package health.maida.lanchonete.config;
import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import health.maida.lanchonete.model.AccountCredentials;
import health.maida.lanchonete.service.TokenAuthenticationService;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	
	public JWTAuthenticationFilter(AuthenticationManager authManager) {
		this.authenticationManager = authManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		try {
		
			AccountCredentials credentials = new ObjectMapper()
					.readValue(request.getInputStream(), AccountCredentials.class);
			
			return this.authenticationManager
					.authenticate(
					new UsernamePasswordAuthenticationToken(
							credentials.getUsername(), 
							credentials.getPassword(), 
							Collections.emptyList()
							)
					);
		
		} catch (IOException e) {
            throw new RuntimeException(e);
        }
	}
	
	@Override
	protected void successfulAuthentication(
			HttpServletRequest request, 
			HttpServletResponse response,
			FilterChain filterChain,
			Authentication auth) throws IOException, ServletException {
		
		String username = ((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername();
		
		TokenAuthenticationService.addAuthentication(response, username);
	}
	 

}
