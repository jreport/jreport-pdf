package br.jreport.pdf.zexamples.demotitle;

import br.jreport.core.api.NewClassReport;
import br.jreport.core.api.NewReport;
import br.jreport.core.impl.Style;

public class DemoReportTitle implements NewClassReport {

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
				.addText("Tribunal Regional Eleitoral do Pará", Style.of("text.title"))
				.addText("Seção de Desenvolvimento de Sistemas", Style.of("text.title"))
				.addText("Relatório de Pontos de Transmissão e Locais de Votação", Style.of("text.subtitle"))
			.buildTitle();
		//@formatter:on
	}

}
