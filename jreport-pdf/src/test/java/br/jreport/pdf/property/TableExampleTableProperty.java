package br.jreport.pdf.property;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import br.jreport.core.api.NewTableColumn;
import br.jreport.core.api.NewTableHeader;
import br.jreport.core.api.datasource.NewDatasource;
import br.jreport.core.api.property.NewTableProperty;
import br.jreport.core.enumerations.HeaderType;
import br.jreport.pdf.datasource.TableExampleDS;
import br.jreport.pdf.model.CategoriaPagamentoModel;

public class TableExampleTableProperty implements NewTableProperty<CategoriaPagamentoModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TableExampleDS pontoTransmissaoDS = new TableExampleDS();

	//@formatter:off
	private List<NewTableHeader> headers = Arrays.asList(
			NewTableHeader.of("Categoria Pagamento", HeaderType.TEXT).get(),
			NewTableHeader.of("JAN", HeaderType.NUMBER).get(),
			NewTableHeader.of("FEV", HeaderType.NUMBER).get(),
			NewTableHeader.of("MAR", HeaderType.NUMBER).get(),
			NewTableHeader.of("ABR", HeaderType.NUMBER).get(),
			NewTableHeader.of("MAI", HeaderType.NUMBER, "classecolspan2").get().setColspan("thead.colspan:2")
			);

	@Override
	public Optional<NewTableColumn> getColumn(CategoriaPagamentoModel item, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return NewTableColumn.of(item.getCategoria());
		case 1:
			return NewTableColumn.of(item.getMeses().get("JAN").toString());
		case 2:
			return NewTableColumn.of(item.getMeses().get("FEV").toString());
		case 3:
			return NewTableColumn.of(item.getMeses().get("MAR").toString());
		case 4:
			return NewTableColumn.of(item.getMeses().get("ABR").toString());
		case 5:
			return NewTableColumn.of(item.getMeses().get("MAI").toString());
		}
		return Optional.empty();
	}

	@Override
	public List<NewTableHeader> getHeaders() {
		return headers;
	}

	@Override
	public NewDatasource<CategoriaPagamentoModel> getDatasource() {
		return this.pontoTransmissaoDS;
	}
	//@formatter:on
}
