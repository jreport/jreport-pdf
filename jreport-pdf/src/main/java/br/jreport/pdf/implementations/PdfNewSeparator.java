package br.jreport.pdf.implementations;

import java.util.Optional;

import com.itextpdf.layout.Document;

import br.jreport.core.api.NewSeparator;
import br.jreport.pdf.PdfReport;
import br.jreport.pdf.helper.DocumentHelper;

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
		PdfReport.addToDocument(document, DocumentHelper.createDefaultSeparator());
	}

	public static Optional<PdfNewSeparator> of(Document document) {
		if (document != null) {
			return Optional.of(new PdfNewSeparator(document));
		}
		return Optional.empty();
	}

}
