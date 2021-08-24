package health.maida.lanchonete.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import health.maida.lanchonete.entity.HistoricoCaixa;

@Repository
public interface HistoricoCaixaRepository extends JpaRepository<HistoricoCaixa, Long> { 

}
