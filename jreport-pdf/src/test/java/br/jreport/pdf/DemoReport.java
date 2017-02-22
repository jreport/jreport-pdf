package br.jreport.pdf;

import br.jreport.core.annotation.JsonStyleClass;
import br.jreport.core.api.NewClassReport;
import br.jreport.core.api.NewReport;
import br.jreport.pdf.datasource.LovalVotacaoDS;
import br.jreport.pdf.datasource.PontoTransmissaoDS;
import br.jreport.pdf.implementations.PdfColspanLine;
import br.jreport.pdf.property.PontoTransmissaoTableProperty;

@JsonStyleClass("demo.report")
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
				.addText("Relatório de Teste")
				.addColspanline(new PdfColspanLine()
						.addHeadersColspan(4)
						.addText("Colspan 1 center", "center")
						.addTable(new PontoTransmissaoTableProperty(), "left")
						.addText("Colspan 2 left", "left")
						.addText("Colspan 3 right", "right"))
				.addText("Abc").addSeparator()
			.buildTitle()
			
			.detail().addList(new PontoTransmissaoDS())
				.addText("Listagem de Pontos de Transmissão")
				.addNewLine()
				.addTable(new PontoTransmissaoTableProperty())
				.addTable(new PontoTransmissaoTableProperty(), (pontoTransmissao, tableRow) -> {
					tableRow.list(new LovalVotacaoDS(pontoTransmissao));
				})
			.buildDetail();
		//@formatter:on
	}

}
