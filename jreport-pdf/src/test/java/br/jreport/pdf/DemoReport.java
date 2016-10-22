package br.jreport.pdf;

import br.jreport.core.api.ClassReport;
import br.jreport.core.api.Report;
import br.jreport.core.api.ReportOutputData;
import br.jreport.pdf.adapter.PontoTransmissaoTableAdapter;
import br.jreport.pdf.datasource.PontoTransmissaoDS;

public class DemoReport implements ClassReport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PontoTransmissaoDS pontoTransmissaoDS = new PontoTransmissaoDS();

	private PontoTransmissaoTableAdapter pontoTransmissaoTA = new PontoTransmissaoTableAdapter();

	@Override
	public ReportOutputData create(Report report) {
		//@formatter:off
		return report
			.title()
				.text("Relatório de Teste")
				.text("Abc")
			.buildTitle()
			.detail()
				.table(pontoTransmissaoDS, pontoTransmissaoTA)
			.buildDetail()
		.buildReport();
		//@formatter:on
	}

}
