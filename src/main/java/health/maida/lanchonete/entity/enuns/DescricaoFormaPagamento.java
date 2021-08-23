package health.maida.lanchonete.entity.enuns;

public enum DescricaoFormaPagamento {
	
	DINHEIRO("DINHEIRO"), 
	CARTAO("CARTAO"),
	TRANSFERENCIA("TRANSFERENCIA"),
	PIX("PIX");
	
	private String descricao;
	
	DescricaoFormaPagamento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
