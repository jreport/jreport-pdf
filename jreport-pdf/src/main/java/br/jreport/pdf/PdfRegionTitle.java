package br.jreport.pdf;

import com.lowagie.text.Document;
import com.lowagie.text.Element;

import br.jreport.core.api.Report;
import br.jreport.core.api.Title;

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
		return this;
	}

	@Override
	public Title newPage() {
		return this;
	}

	@Override
	public Report buildTitle() {
		return this.report;
	}

}
