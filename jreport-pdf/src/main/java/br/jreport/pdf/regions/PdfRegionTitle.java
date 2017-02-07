package br.jreport.pdf.regions;

import com.lowagie.text.Document;

import br.jreport.core.api.Report;
import br.jreport.core.api.Title;
import br.jreport.pdf.implementations.PdfNewLine;
import br.jreport.pdf.implementations.PdfNewPage;
import br.jreport.pdf.implementations.PdfText;

public class PdfRegionTitle implements Title {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Document document;

	private Report report;

	public PdfRegionTitle(Document document, Report report) {
		super();
		this.document = document;
		this.report = report;
	}

	@Override
	public Title image(String src) {
		return null;
	}

	@Override
	public Title text(String text) {
		PdfText.of(document, text).ifPresent(txt -> txt.build());
		return this;
	}

	@Override
	public Title newLine() {
		PdfNewLine.of(document).ifPresent(newLine -> newLine.build());
		return this;
	}

	@Override
	public Title newPage() {
		PdfNewPage.of(document).ifPresent(newPage -> newPage.build());
		return this;
	}

	@Override
	public Report buildTitle() {
		return this.report;
	}

}
