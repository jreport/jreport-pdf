package br.jreport.pdf.property;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.jreport.core.api.NewTableColumn;
import br.jreport.core.api.NewTableHeader;
import br.jreport.core.api.datasource.NewDatasource;
import br.jreport.core.api.property.NewTableProperty;
import br.jreport.pdf.datasource.PontoTransmissaoDS;
import br.jreport.pdf.model.PontoTransmissao;

public class PontoTransmissaoTableProperty implements NewTableProperty<PontoTransmissao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PontoTransmissaoDS pontoTransmissaoDS = new PontoTransmissaoDS();

	private List<String> headers = Arrays.asList("Id", "Nome", "aa");

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
		return headers.stream()
				.map(NewTableHeader::of)
				.filter(op -> op.isPresent())
				.map(th -> th.get())
				.collect(Collectors.toList());
		//@formatter:on
	}

	@Override
	public NewDatasource<PontoTransmissao> getDatasource() {
		return this.pontoTransmissaoDS;
	}

}
