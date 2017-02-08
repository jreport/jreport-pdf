package br.jreport.pdf.implementations;

import java.util.Optional;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.draw.LineSeparator;

import br.jreport.core.api.NewSeparator;
import br.jreport.pdf.PdfReport;

public class PdfNewSeparator implements NewSeparator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Document document;

	private PdfNewSeparator() {
		super();
	}

	private PdfNewSeparator(Document document) {
		super();
		this.document = document;
	}

	@Override
	public void build() {
		LineSeparator separator = new LineSeparator();
		separator.setLineWidth(0.1f);
		PdfReport.addToDocument(document, new Chunk(separator));
	}

	public static Optional<PdfNewSeparator> of(Document document) {
		if (document != null) {
			return Optional.of(new PdfNewSeparator(document));
		}
		return Optional.empty();
	}

}
