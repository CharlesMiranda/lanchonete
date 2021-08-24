package health.maida.lanchonete.entity.enuns;

public enum SituacaoPagamento {
	
	ABERTO("AGUARDANDO"), 
	PAGO("PAGO"),
	PAGO_CREDITO("PAGO E CREDITADO"),
	CANCELADO("CANCELADO");
	
	private String descricao;
	
	SituacaoPagamento(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
