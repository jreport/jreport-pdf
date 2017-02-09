package br.jreport.pdf.implementations;

import java.util.Optional;

import com.google.common.base.Strings;
import com.lowagie.text.Document;

import br.jreport.core.api.Text;
import br.jreport.pdf.PdfReport;
import br.jreport.pdf.helper.DocumentHelper;

public class PdfText implements Text {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String text;

	private String style;

	private Document document;

	private PdfText() {
		super();
	}

	private PdfText(Document document, String text) {
		super();
		this.document = document;
		this.text = text;
	}

	private PdfText(Document document, String text, String style) {
		super();
		this.document = document;
		this.text = text;
		this.style = style;
	}

	@Override
	public void build() {
		if (style != null) {
			PdfReport.addToDocument(document, DocumentHelper.createText(text));
		} else {
			PdfReport.addToDocument(document, DocumentHelper.createText(text, style));
		}
	}

	public static Optional<Text> of(Document document, String text) {
		if (document != null && !Strings.isNullOrEmpty(text)) {
			return Optional.of(new PdfText(document, text));
		}
		return Optional.empty();
	}

	public static Optional<Text> of(Document document, String text, String style) {
		if (document != null && !Strings.isNullOrEmpty(text) && !Strings.isNullOrEmpty(style)) {
			return Optional.of(new PdfText(document, text, style));
		}
		return Optional.empty();
	}

}
