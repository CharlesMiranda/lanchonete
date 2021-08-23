package health.maida.lanchonete.entity.enuns;

public enum SituacaoPagamento {
	
	ABERTO(1, "AGUARDANDO"), 
	PAGO(2, "PAGO"),
	PAGO_CREDITO(3, "PAGO E CREDITADO"),
	CANCELADO(4, "CANCELADO");
	
	private int id;
	private String descricao;
	
	SituacaoPagamento(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
