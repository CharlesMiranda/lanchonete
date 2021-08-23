package health.maida.lanchonete.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import health.maida.lanchonete.entity.enuns.SituacaoPagamento;
import health.maida.lanchonete.entity.enuns.SituacaoPedido;
import lombok.Builder;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Entity
@Builder
public class HistoricoCaixa  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private Cliente cliente;
    
    @OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    Set<ItemPedido> itensPedido;
    
    @Column(nullable = false)
    private Double valorTotal;
    
    @Column(nullable = false)
    private Date created_at;

    @Column(nullable = true)
    private Date modified_at;
    
    @Enumerated(EnumType.ORDINAL)
	private SituacaoPedido situacaoPedido;	
    
    @Enumerated(EnumType.ORDINAL)
	private SituacaoPagamento situacaoPagamento;
    
    @Column(nullable = true)
    private String localPagamento;
    
    @OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    Set<FormaPagamento> formasPagamento;
    
    @JoinColumn(name = "pedido_id", nullable = false)
    @Column(nullable = false)
    private Pedido pedido;

}
