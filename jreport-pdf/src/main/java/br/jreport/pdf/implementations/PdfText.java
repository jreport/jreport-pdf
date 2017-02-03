package br.jreport.pdf;

import java.util.Optional;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;

import br.jreport.core.api.Text;

public class PdfText implements Text {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String text;

	private Document document;

	private PdfText() {
		super();
	}

	private PdfText(Document document, String text) {
		super();
		this.document = document;
		this.text = text;
	}

	@Override
	public void build() {
		Paragraph paragraph = new Paragraph();
		paragraph.add(text);
		PdfReport.addToDocument(document, paragraph);
	}

	public static Optional<Text> of(Document document, String text) {
		if (document != null && text != null) {
			return Optional.of(new PdfText(document, text));
		}
		return Optional.empty();
	}

}
