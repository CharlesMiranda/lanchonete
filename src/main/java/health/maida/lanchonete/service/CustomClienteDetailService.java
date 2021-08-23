package health.maida.lanchonete.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import health.maida.lanchonete.repository.ClienteRepository;

@Service
public class CustomClienteDetailService implements UserDetailsService{
	
	private final ClienteRepository _clienteRepository;

    @Autowired
    public CustomClienteDetailService(ClienteRepository clienteRepository) {
        this._clienteRepository = clienteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
    	return Optional.ofNullable(_clienteRepository.findByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("Cliente não encontrado"));

    }

}
