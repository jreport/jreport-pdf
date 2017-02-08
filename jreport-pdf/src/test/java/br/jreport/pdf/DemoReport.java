package br.jreport.pdf;

import br.jreport.core.annotation.JsonStyleClass;
import br.jreport.core.api.ClassReport;
import br.jreport.core.api.Report;
import br.jreport.pdf.datasource.LovalVotacaoDS;
import br.jreport.pdf.datasource.PontoTransmissaoDS;
import br.jreport.pdf.property.PontoTransmissaoTableProperty;

@JsonStyleClass("demo.report")
public class DemoReport implements ClassReport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Report templateReport(Report report) {
		//@formatter:off
		return report
			.title()
				.addText("Relatório de Teste")
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
