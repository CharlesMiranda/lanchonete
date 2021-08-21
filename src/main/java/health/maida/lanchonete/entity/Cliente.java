package health.maida.lanchonete.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Cliente {
	
	@Id
	@Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    @JsonFormat(pattern="dd/MM/yyyy")
	private Date dataNascimento;
    
    @Column(nullable = false)
    private String telefone;
    
    @Column(nullable = false)
    private String senha;
    

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
