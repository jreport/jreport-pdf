package br.jreport.pdf;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;

import br.jreport.core.api.Report;
import br.jreport.core.api.Title;

public class PdfReportTitle implements Title {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Document document;

	private Report report;

	public PdfReportTitle(Document document, Report report) {
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
		try {
			this.document.add(new Paragraph(text));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return this;
	}

	@Override
	public Title line() {
		return null;
	}

	@Override
	public Title line(int value) {
		return null;
	}

	@Override
	public Report buildTitle() {
		return this.report;
	}

}
