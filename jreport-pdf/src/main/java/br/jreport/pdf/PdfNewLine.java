package br.jreport.pdf;

import java.util.Optional;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;

import br.jreport.core.api.NewLine;

public class PdfNewLine implements NewLine<Chunk> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Document document;

	private PdfNewLine() {
		super();
	}

	private PdfNewLine(Document document) {
		super();
		this.document = document;
	}

	@Override
	public void build() {
		PdfReport.addToDocument(document, Chunk.NEWLINE);
	}

	public static Optional<PdfNewLine> of(Document document) {
		if (document != null) {
			return Optional.of(new PdfNewLine(document));
		}
		return Optional.empty();
	}

}
