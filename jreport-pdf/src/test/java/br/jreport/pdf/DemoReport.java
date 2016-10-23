package br.jreport.pdf;

import br.jreport.core.api.ClassReport;
import br.jreport.core.api.Report;
import br.jreport.core.api.ReportOutputData;
import br.jreport.pdf.adapter.PontoTransmissaoTableAdapter;

public class DemoReport implements ClassReport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//private PontoTransmissaoTableAdapter pontoTransmissaoTA = new PontoTransmissaoTableAdapter();

	@Override
	public ReportOutputData create(Report report) {
		//@formatter:off
		return report
			.title()
				.text("Relatório de Teste")
				.text("Abc")
			.buildTitle()
			.detail()
				.text("Listagem de Pontos de Transmissão")
				.newLine()
				.table(new PontoTransmissaoTableAdapter())
			.buildDetail()
		.buildReport();
		//@formatter:on
	}

}
