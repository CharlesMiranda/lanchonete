package health.maida.lanchonete.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import health.maida.lanchonete.entity.enuns.DescricaoFormaPagamento;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
public class FormaPagamento  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(nullable = false)
    private Double valor;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pedido_id", nullable = false)
    @Column(nullable = false)
    private Pedido pedido;
    
    @Column(nullable = false)
    private Date created_at;
    
    @Enumerated(EnumType.STRING)
	private DescricaoFormaPagamento descricao;
    
    
}
