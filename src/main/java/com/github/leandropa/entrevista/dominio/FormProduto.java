package com.github.leandropa.entrevista.dominio;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe usada para guardar os valores de um formulário
 * para validação.
 */
public class FormProduto {

	private long id = -1;

	private String nome;

	private String preco;

	private String quantidade;

	private String dataDeCadastro;

	public FormProduto() {
		dataDeCadastro = new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());
	}
	public FormProduto(Produto produto) {
		nome = produto.getNome();
		preco = produto.getPreco().toString();
		quantidade = Integer.toString(produto.getQuantidade());
		dataDeCadastro = new SimpleDateFormat("yyyy-MM-dd").format(produto.getDataDeCadastro());
		id = produto.getId();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public String getDataDeCadastro() {
		return dataDeCadastro;
	}

	public void setDataDeCadastro(String dataDeCadastro) {
		this.dataDeCadastro = dataDeCadastro;
	}

	public Produto validate() {
		Date dataRegistro = null;
		BigDecimal preco = null;
		int quantidade = 0;
		System.out.println("Validando produto...");
		try {
			System.out.println("Validado 5");
			dataRegistro = new SimpleDateFormat("yyyy-MM-dd").parse(getDataDeCadastro());
			System.out.println("Validado 4");
			preco = new BigDecimal(getPreco());
			System.out.println("Validado 2");
			quantidade = Integer.valueOf(getQuantidade());
			System.out.println("Validado 1");
			if (getNome().isEmpty() || quantidade <= 0 ||
					preco.compareTo(new BigDecimal(0)) <= 0 || dataRegistro == null)
				return null;
		} catch (Exception e) {
			// Caso alguma exceção ocorreu, significa que algum valor
			// não está correto (nulo ou mal-formado)
			System.out.println("Printando... " + e.getMessage());
			e.printStackTrace();
			return null;
		}
		System.out.println("Validado");
		Produto p = new Produto();
		p.setNome(getNome());
		p.setPreco(preco);
		p.setQuantidade(quantidade);
		p.setDataDeCadastro(dataRegistro);
		return p;
	}
}
