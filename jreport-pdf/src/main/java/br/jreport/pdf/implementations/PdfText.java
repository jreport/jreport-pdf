package br.jreport.pdf.implementations;

import java.util.Optional;

import com.google.common.base.Strings;
import com.itextpdf.layout.Document;

import br.jreport.core.api.NewText;
import br.jreport.pdf.PdfReport;
import br.jreport.pdf.helper.DocumentHelper;

public class PdfText implements NewText {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String text;

	private String style = null;

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
		PdfReport.addToDocument(document, DocumentHelper.createText(text, style));
	}

	public static Optional<NewText> of(Document document, String text) {
		if (document != null && !Strings.isNullOrEmpty(text)) {
			return Optional.of(new PdfText(document, text));
		}
		return Optional.empty();
	}

	public static Optional<NewText> of(Document document, String text, String style) {
		if (document != null && !Strings.isNullOrEmpty(text) && !Strings.isNullOrEmpty(style)) {
			return Optional.of(new PdfText(document, text, style));
		}
		return Optional.empty();
	}

}
