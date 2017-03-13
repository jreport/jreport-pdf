package br.jreport.pdf.implementations;

import java.util.Optional;

import com.google.common.base.Strings;
import com.itextpdf.layout.Document;

import br.jreport.core.api.interfaces.NewText;
import br.jreport.core.impl.Style;
import br.jreport.pdf.PdfReport;
import br.jreport.pdf.helper.DocumentHelper;

public class PdfText implements NewText {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String text;

	private Optional<Style> style = null;

	private Document document;

	private PdfText() {
		super();
	}

	private PdfText(Document document, String text) {
		super();
		this.document = document;
		this.text = text;
	}

	private PdfText(Document document, String text, Optional<Style> style) {
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

	public static Optional<NewText> of(Document document, String text, Optional<Style> style) {
		if (document != null && !Strings.isNullOrEmpty(text) && style.isPresent()) {
			return Optional.of(new PdfText(document, text, style));
		}
		return Optional.empty();
	}

}
