package com.github.leandropa.entrevista.dominio;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Produto {

	@Id
	@GeneratedValue
	private long id;

	private String nome;

	private BigDecimal preco;

	private int quantidade = 1;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataDeCadastro = new Date();

	public Produto() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Date getDataDeCadastro() {
		return dataDeCadastro;
	}

	public void setDataDeCadastro(Date dataDeCadastro) {
		this.dataDeCadastro = dataDeCadastro;
	}

	/**
	 * Checa se os atributos são válidos
	 * @return true caso os atributos são válidos.
	 */
	public boolean validate() {
		return nome != null && !nome.isEmpty() &&
				preco != null && preco.compareTo(new BigDecimal(0)) > 0	&&
				quantidade > 0 && dataDeCadastro != null;
	}

	@Override
	public String toString() {
		return "Produto{" + id + ", " + nome + ", " + preco + ", " + quantidade + ", " + dataDeCadastro + "}";
	}
}
