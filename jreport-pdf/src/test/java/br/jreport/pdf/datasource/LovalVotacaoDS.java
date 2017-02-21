package br.jreport.pdf.datasource;

import java.util.List;
import java.util.Optional;

import br.jreport.core.api.datasource.NewDatasource;
import br.jreport.pdf.model.LocalVotacao;
import br.jreport.pdf.model.PontoTransmissao;

public class LovalVotacaoDS implements NewDatasource<LocalVotacao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LovalVotacaoDS(PontoTransmissao pontoTransmissao) {
		super();
		this.pontoTransmissao = pontoTransmissao;
	}

	private PontoTransmissao pontoTransmissao;

	@Override
	public List<LocalVotacao> getList() {
		return pontoTransmissao.getLocaisVotacao();
	}

	@Override
	public Optional<String> emptyMessage() {
		// TODO Auto-generated method stub
		return null;
	}

}
