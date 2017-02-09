package br.jreport.pdf.regions;


import com.lowagie.text.Document;

import br.jreport.core.api.Report;
import br.jreport.core.api.Title;
import br.jreport.pdf.implementations.PdfImage;
import br.jreport.pdf.implementations.PdfNewLine;
import br.jreport.pdf.implementations.PdfNewPage;
import br.jreport.pdf.implementations.PdfNewSeparator;
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
	public Title addText(String text) {
		PdfText.of(document, text).ifPresent(txt -> txt.build());
		return this;
	}

	@Override
	public Title addText(String text, String styleClass) {
		PdfText.of(document, text, styleClass).ifPresent(txt -> txt.build());
		return this;
	}

	@Override
	public Title addImage(String src) {
		PdfImage.of(document, src).ifPresent(newImage -> newImage.build());
		return null;
	}

	@Override
	public Title addImage(String src, String classe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Title addNewLine() {
		PdfNewLine.of(document).ifPresent(newLine -> newLine.build());
		return this;
	}

	@Override
	public Title addSeparator() {
		PdfNewSeparator.of(document).ifPresent(newSeparator -> newSeparator.build());
		return this;
	}

	@Override
	public Title addNewPage() {
		PdfNewPage.of(document).ifPresent(newPage -> newPage.build());
		return this;
	}

	@Override
	public Report buildTitle() {
		return this.report;
	}

}
