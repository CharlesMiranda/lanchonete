package health.maida.lanchonete.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
public class Produto  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String descricao;
    
    @Column(nullable = false)
    private Double preco;
    
    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false)
    private int habilitadoCestaVenda;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getHabilitadoCestaVenda() {
		return habilitadoCestaVenda;
	}

	public void setHabilitadoCestaVenda(int habilitadoCestaVenda) {
		this.habilitadoCestaVenda = habilitadoCestaVenda;
	}
    
    
}
