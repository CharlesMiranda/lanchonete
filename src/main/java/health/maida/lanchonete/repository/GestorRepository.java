package health.maida.lanchonete.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import health.maida.lanchonete.entity.Gestor;

@Repository
public interface GestorRepository extends JpaRepository<Gestor, Long> { 
	
	@Query("select g from Gestor g where g.email = ?1")
    Optional<Gestor> pesquisarPorEmail(String email);
	
	Gestor findByUsername(String username);
	
	@Query("select g from Gestor g where g.email = ?1 and g.password = ?2")
    Optional<Gestor> pesquisarPorEmailESenha(String email, String password);
	
	
	
}
