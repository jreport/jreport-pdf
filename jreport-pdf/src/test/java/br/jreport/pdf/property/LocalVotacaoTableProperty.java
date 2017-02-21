package br.jreport.pdf.property;

import java.util.List;
import java.util.Optional;

import br.jreport.core.api.NewTableColumn;
import br.jreport.core.api.NewTableHeader;
import br.jreport.core.api.datasource.NewDatasource;
import br.jreport.core.api.property.NewTableProperty;
import br.jreport.pdf.datasource.LovalVotacaoDS;
import br.jreport.pdf.model.LocalVotacao;
import br.jreport.pdf.model.PontoTransmissao;

public class LocalVotacaoTableProperty implements NewTableProperty<LocalVotacao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PontoTransmissao pontoTransmissao;
	
	

	public LocalVotacaoTableProperty(PontoTransmissao pontoTransmissao) {
		super();
		this.pontoTransmissao = pontoTransmissao;
	}

	@Override
	public NewDatasource<LocalVotacao> getDatasource() {
		return new LovalVotacaoDS(pontoTransmissao);
	}

	@Override
	public Optional<NewTableColumn> getColumn(LocalVotacao item, int columnIndex) {
		return null;
	}

	@Override
	public List<NewTableHeader> getHeaders() {
		return null;
	}

}
