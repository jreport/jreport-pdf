package br.jreport.pdf.implementations;

import java.util.Optional;

import com.itextpdf.layout.Document;

import br.jreport.core.api.NewColspanLine;
import br.jreport.pdf.PdfReport;
import br.jreport.pdf.helper.DocumentHelper;

//TODO Fazer ajustes nessa implementaçãpo
public class PdfColspanLine implements NewColspanLine {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Document document;

	private PdfColspanBody child;

	public PdfColspanBody addHeadersColspan(Integer cols) {
		child = new PdfColspanBody(cols);
		return child;
	}

	public PdfColspanLine() {

	}

	private PdfColspanLine(Document document, PdfColspanBody child) {
		this.document = document;
		this.child = child;
	}

	@Override
	public void build() {
		PdfReport.addToDocument(document, DocumentHelper.createColspan(child.getHeadersColspan(), child.getCells()));
	}

	public static Optional<PdfColspanLine> of(Document document, PdfColspanBody child) {
		if (document != null) {
			return Optional.of(new PdfColspanLine(document, child));
		}
		return Optional.empty();
	}

}
