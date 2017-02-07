package br.jreport.pdf;

import br.jreport.core.annotation.JsonStyleClass;
import br.jreport.core.api.ClassReport;
import br.jreport.core.api.Report;
import br.jreport.core.api.ReportOutputData;
import br.jreport.pdf.datasource.LovalVotacaoDS;
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
				.text("Relatório de Teste")
				.text("Abc")
			.buildTitle()
			.detail()
				.text("Listagem de Pontos de Transmissão")
				.newLine()
				.table(new PontoTransmissaoTableProperty())
				.table(new PontoTransmissaoTableProperty(), (pontoTransmissao, tableRow) -> {
					tableRow.list(new LovalVotacaoDS(pontoTransmissao));
				})
			.buildDetail();
		//@formatter:on
	}

}
