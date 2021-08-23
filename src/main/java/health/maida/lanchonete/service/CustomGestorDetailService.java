package health.maida.lanchonete.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import health.maida.lanchonete.repository.GestorRepository;

@Service
public class CustomGestorDetailService implements UserDetailsService{
	
	private final GestorRepository _gestorRepository;

    @Autowired
    public CustomGestorDetailService(GestorRepository gestorRepository) {
        this._gestorRepository = gestorRepository;
        
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
    	return Optional.ofNullable(_gestorRepository.findByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("Gestor não encontrado"));

    }

}
