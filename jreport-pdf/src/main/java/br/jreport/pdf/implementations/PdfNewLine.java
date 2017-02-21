package br.jreport.pdf.implementations;

import java.util.Optional;

import com.itextpdf.layout.Document;

import br.jreport.core.api.NewLine;
import br.jreport.pdf.PdfReport;
import br.jreport.pdf.helper.DocumentHelper;

public class PdfNewLine implements NewLine {

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
		PdfReport.addToDocument(document, DocumentHelper.newLine());
	}

	public static Optional<PdfNewLine> of(Document document) {
		if (document != null) {
			return Optional.of(new PdfNewLine(document));
		}
		return Optional.empty();
	}

}
