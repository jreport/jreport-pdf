package br.jreport.pdf;

import br.jreport.core.api.NewClassReport;
import br.jreport.core.api.NewReport;
import br.jreport.core.impl.Style;
import br.jreport.pdf.implementations.PdfColspanLine;
import br.jreport.pdf.property.PontoTransmissaoTableProperty;

public class DemoReport implements NewClassReport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public NewReport templateReport(NewReport report) {
		//@formatter:off
		return report
			.title()
			.addBrasao()
				.addText("Relatório de Teste", Style.of("text.title"))
				.addText("TRE-PA 2017", Style.of("text.subtitle"))
				.addColspanline(new PdfColspanLine()
						.addHeadersColspan(5)
						.addEmptyCol()
						.addText("Colspan 1 center", Style.of("classe_definida", "colspan:2"))
						.addText("Colspan 2 left")
						.addText("Colspan 3 right"))
				.addText("Abc").addSeparator()
			.buildTitle()
//			
			.detail()
//				.addList(new PontoTransmissaoDS())
//				.addText("Listagem de Pontos de Transmissão")
//				.addNewLine()
				.addTable(new PontoTransmissaoTableProperty(), Style.of("table-custom"))
//				.addTable(new PontoTransmissaoTableProperty(), (pontoTransmissao, tableRow) -> {
//					tableRow.list(new LovalVotacaoDS(pontoTransmissao));
//				})
			.buildDetail()
			;
		//@formatter:on
	}

}
