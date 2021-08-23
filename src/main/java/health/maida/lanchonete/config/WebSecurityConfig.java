package health.maida.lanchonete.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import health.maida.lanchonete.service.CustomClienteDetailService;
import health.maida.lanchonete.service.CustomGestorDetailService;
import health.maida.lanchonete.utils.Seguranca;
import lombok.extern.log4j.Log4j2;


@EnableWebSecurity
@Log4j2
@EnableGlobalMethodSecurity(prePostEnabled = true)
@SuppressWarnings("java:S5344")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private CustomGestorDetailService customGestorDetailService;
	
	@Autowired
    private CustomClienteDetailService customClienteDetailService;

	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable()
		      .authorizeRequests()
			  .antMatchers(HttpMethod.GET, SecurityConstants.SIGN_UP_URL_GET).permitAll()
		      .antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL_POST).permitAll()
		      .anyRequest()
		      .authenticated()
		      .and()
		      .formLogin()
		      .and()
		      .httpBasic();
	
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// cria uma conta default
		/*auth.inMemoryAuthentication()
			.withUser("gestor12")
			.password(Seguranca.criptografarSenha("minhassenhagestor1"))
			.roles("GESTOR")
			.and()
			.withUser("cliente12")
			.password(Seguranca.criptografarSenha("minhasenhacliente1"))
			.roles("CLIENTE");*/
		
		auth.userDetailsService(customGestorDetailService).passwordEncoder(Seguranca.getCodificadorSenha());
		auth.userDetailsService(customClienteDetailService).passwordEncoder(Seguranca.getCodificadorSenha());
	
	}
	
}
