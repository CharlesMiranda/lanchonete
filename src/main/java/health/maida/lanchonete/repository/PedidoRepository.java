package health.maida.lanchonete.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import health.maida.lanchonete.entity.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	@Query("select p from Pedido p join Cliente c where c.email = ?1")
	Optional<List<Pedido>> pesquisarPorCliente(String email);

}
