package br.jreport.pdf.zexamples.relatorioexemplo;

import br.jreport.core.api.NewClassReport;
import br.jreport.core.api.NewReport;
import br.jreport.core.impl.Style;
import br.jreport.pdf.implementations.PdfColspanLine;
import br.jreport.pdf.property.TableExampleTableProperty;

public class DemoReportTable implements NewClassReport {

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
				.addText("Teste colspan line", Style.of("text.subtitle"))
				.addColspanline(new PdfColspanLine()
						.addHeadersColspan(4)
						.addText("Elementos lado ")
						.addText("a lado", Style.of(""))
						.addEmptyCol()
						.addImage("tux.png", Style.of("image")))
			.buildTitle()
			.detail()
				.addTable(new TableExampleTableProperty(), Style.of(""))
			.buildDetail();
		//@formatter:on
	}

}
