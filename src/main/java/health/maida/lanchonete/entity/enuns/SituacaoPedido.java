package health.maida.lanchonete.entity.enuns;

public enum SituacaoPedido {
	
	AGUARDANDO(1, "Aguardando"), 
	SENDO_PREPARADO(2, "Sendo preparado"),
	PRONTO(3, "Pronto"),
	ENTREGUE(4, "Entregue"),
	CANCELADO(5, "Cancelado");
	
	private int id;
	private String descricao;
	
	SituacaoPedido(int id, String descricao) {
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
