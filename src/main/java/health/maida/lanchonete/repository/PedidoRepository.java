package health.maida.lanchonete.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import health.maida.lanchonete.entity.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> { 

}
