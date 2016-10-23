package br.jreport.pdf.property;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.jreport.core.api.TableColumn;
import br.jreport.core.api.TableHeader;
import br.jreport.core.api.datasource.Datasource;
import br.jreport.core.api.property.TableProperty;
import br.jreport.pdf.datasource.PontoTransmissaoDS;
import br.jreport.pdf.model.PontoTransmissao;

public class PontoTransmissaoTableProperty implements TableProperty<PontoTransmissao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PontoTransmissaoDS pontoTransmissaoDS = new PontoTransmissaoDS();

	private List<String> headers = Arrays.asList("Id", "Nome");

	@Override
	public Optional<TableColumn> getColumn(PontoTransmissao item, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return TableColumn.of(item.getId());
		case 1:
			return TableColumn.of(item.getNome());
		}
		return Optional.empty();
	}

	@Override
	public List<TableHeader> getHeaders() {
		//@formatter:off
		return headers.stream()
				.map(TableHeader::of)
				.filter(op -> op.isPresent())
				.map(th -> th.get())
				.collect(Collectors.toList());
		//@formatter:on
	}

	@Override
	public Datasource<PontoTransmissao> getDatasource() {
		return this.pontoTransmissaoDS;
	}

}
