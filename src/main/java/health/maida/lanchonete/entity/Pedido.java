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
public class Pedido  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

	@JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
    
    @OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    Set<ItemPedido> itensPedido;
    
    @Column(nullable = false)
    private Double valorTotal;
    
    @Column(nullable = false)
    private Date createdAt;

    @Column(nullable = true)
    private Date modifiedAt;
    
    @Enumerated(EnumType.STRING)
	private SituacaoPedido situacaoPedido;	
    
    @Enumerated(EnumType.STRING)
	private SituacaoPagamento situacaoPagamento;
    
    @Column(nullable = true)
    private String localPagamento;
    
    @OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    Set<FormaPagamento> formasPagamento;
    
    
    public Pedido() {
    	
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<ItemPedido> getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido(Set<ItemPedido> itensPedido) {
		this.itensPedido = itensPedido;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public SituacaoPedido getSituacaoPedido() {
		return situacaoPedido;
	}

	public void setSituacaoPedido(SituacaoPedido situacaoPedido) {
		this.situacaoPedido = situacaoPedido;
	}
	
	public void setSituacaoPagamentoAberto() {
		this.situacaoPagamento = SituacaoPagamento.ABERTO;
	}
	
	public void setSituacaoPedidoAguardando() {
		this.situacaoPedido = SituacaoPedido.AGUARDANDO;
	}
	
	public void setSituacaoPedidoCancelado() {
		this.situacaoPedido = SituacaoPedido.CANCELADO;
	}

	public SituacaoPagamento getSituacaoPagamento() {
		return situacaoPagamento;
	}

	public void setSituacaoPagamento(SituacaoPagamento situacaoPagamento) {
		this.situacaoPagamento = situacaoPagamento;
	}

	public String getLocalPagamento() {
		return localPagamento;
	}

	public void setLocalPagamento(String localPagamento) {
		this.localPagamento = localPagamento;
	}

	public Set<FormaPagamento> getFormasPagamento() {
		return formasPagamento;
	}

	public void setFormasPagamento(Set<FormaPagamento> formasPagamento) {
		this.formasPagamento = formasPagamento;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
    
    
}
