package br.jreport.pdf.property;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import br.jreport.core.api.NewTableColumn;
import br.jreport.core.api.NewTableHeader;
import br.jreport.core.api.datasource.NewDatasource;
import br.jreport.core.api.property.NewTableProperty;
import br.jreport.core.enumerations.HeaderType;
import br.jreport.pdf.datasource.PontoTransmissaoDS;
import br.jreport.pdf.model.PontoTransmissao;

public class PontoTransmissaoTableProperty implements NewTableProperty<PontoTransmissao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PontoTransmissaoDS pontoTransmissaoDS = new PontoTransmissaoDS();

	private List<NewTableHeader> headers = Arrays.asList(NewTableHeader.of("Zona", HeaderType.TEXT).get(),
			NewTableHeader.of("Quantidade de Pontos", HeaderType.NUMBER).get());

	@Override
	public Optional<NewTableColumn> getColumn(PontoTransmissao item, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return NewTableColumn.of(item.getId());
		case 1:
			return NewTableColumn.of(item.getNome());
		}
		return Optional.empty();
	}

	@Override
	public List<NewTableHeader> getHeaders() {
		//@formatter:off
		return headers;
		//@formatter:on
	}

	@Override
	public NewDatasource<PontoTransmissao> getDatasource() {
		return this.pontoTransmissaoDS;
	}

}
