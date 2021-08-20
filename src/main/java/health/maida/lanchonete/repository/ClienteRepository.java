package health.maida.lanchonete.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import health.maida.lanchonete.entity.Cliente;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> { 
	
	@Query("select c from Cliente c where c.email = ?1")
    Optional<Cliente> pesquisarPorEmail(String email);
	
}
