package health.maida.lanchonete.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import health.maida.lanchonete.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> { 
	@Query("select p from Produto p where p.descricao = ?1")
    Optional<Produto> pesquisarDescricao(String descricao);
	
	@Query("select p from Produto p where p.descricao = ?1 and p.id <> ?2")
    Optional<Produto> pesquisarDescricaoDiferenteId(String descricao, long id);
}
