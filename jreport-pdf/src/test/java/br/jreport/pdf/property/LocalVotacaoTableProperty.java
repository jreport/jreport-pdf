package br.jreport.pdf.property;

import java.util.List;
import java.util.Optional;

import br.jreport.core.api.TableColumn;
import br.jreport.core.api.TableHeader;
import br.jreport.core.api.datasource.Datasource;
import br.jreport.core.api.property.TableProperty;
import br.jreport.pdf.datasource.LovalVotacaoDS;
import br.jreport.pdf.model.LocalVotacao;
import br.jreport.pdf.model.PontoTransmissao;

public class LocalVotacaoTableProperty implements TableProperty<LocalVotacao> {

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
	public Datasource<LocalVotacao> getDatasource() {
		return new LovalVotacaoDS(pontoTransmissao);
	}

	@Override
	public Optional<TableColumn> getColumn(LocalVotacao item, int columnIndex) {
		return null;
	}

	@Override
	public List<TableHeader> getHeaders() {
		return null;
	}

}
