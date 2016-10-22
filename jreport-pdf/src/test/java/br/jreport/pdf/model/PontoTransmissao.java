package br.jreport.pdf.model;

import java.io.Serializable;
import java.util.List;

public class PontoTransmissao implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String nome;

	private List<LocalVotacao> locaisVotacao;

	public PontoTransmissao() {
		super();
	}

	public PontoTransmissao(String id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<LocalVotacao> getLocaisVotacao() {
		return locaisVotacao;
	}

	public void setLocaisVotacao(List<LocalVotacao> locaisVotacao) {
		this.locaisVotacao = locaisVotacao;
	}

}
