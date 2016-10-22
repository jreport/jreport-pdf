package br.jreport.pdf;

import br.jreport.core.api.ClassReport;
import br.jreport.core.api.Report;
import br.jreport.core.api.ReportOutputData;

public class DemoReport implements ClassReport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public ReportOutputData create(Report report) {
		//@formatter:off
		return report
			.title()
				.text("Relatório de Teste")
				.text("Abc")
			.buildTitle()
		.buildReport();
		//@formatter:on
	}

}
