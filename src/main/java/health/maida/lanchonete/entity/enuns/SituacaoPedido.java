package health.maida.lanchonete.entity.enuns;

public enum SituacaoPedido {
	
	AGUARDANDO("Aguardando"), 
	SENDO_PREPARADO("Sendo preparado"),
	PRONTO("Pronto"),
	ENTREGUE("Entregue"),
	CANCELADO("Cancelado");
	
	private String descricao;
	
	SituacaoPedido(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
