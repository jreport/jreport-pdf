package br.jreport.pdf.zexamples.demodetail;

import br.jreport.core.api.NewClassReport;
import br.jreport.core.api.NewReport;
import br.jreport.core.impl.Style;
import br.jreport.pdf.implementations.PdfColspanLine;
import br.jreport.pdf.property.PontoTransmissaoTableProperty;

public class DemoReportColspanLine implements NewClassReport {

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
				.addText("Teste colspan line", Style.of("text.title", "color:red"))
				.addColspanline(new PdfColspanLine()
						.addHeadersColspan(4)
						.addText("Elementos lado ")
						.addText("a lado", Style.of(""))
						.addEmptyCol()
						.addImage("tux.png", Style.of("image")))
			.buildTitle()
			.detail()
				.addColspanline(new PdfColspanLine()
				.addHeadersColspan(3)
				.addText("Com tabela ")
				.addEmptyCol().addTable(new PontoTransmissaoTableProperty()))
			.buildDetail();
		//@formatter:on
	}

}
