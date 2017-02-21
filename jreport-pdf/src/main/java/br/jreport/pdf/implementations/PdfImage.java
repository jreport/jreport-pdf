package br.jreport.pdf.implementations;

import java.util.Optional;

import com.google.common.base.Strings;
import com.itextpdf.layout.Document;

import br.jreport.core.api.NewImage;
import br.jreport.pdf.PdfReport;
import br.jreport.pdf.helper.DocumentHelper;

public class PdfImage implements NewImage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String src;

	private String style = null;

	private Document document;

	private PdfImage() {
		super();
	}

	private PdfImage(Document document, String src) {
		super();
		this.document = document;
		this.src = src;
	}

	private PdfImage(Document document, String src, String style) {
		super();
		this.document = document;
		this.src = src;
	}

	@Override
	public void build() {
		PdfReport.addToDocument(document, DocumentHelper.loadImage(src, style));
	}

	public static Optional<PdfImage> of(Document document, String src) {
		if (document != null && src != null) {
			return Optional.of(new PdfImage(document, src));
		}
		return Optional.empty();
	}

	public static Optional<PdfImage> of(Document document, String src, String classe) {
		if (document != null && !Strings.isNullOrEmpty(src) && !Strings.isNullOrEmpty(classe)) {
			return Optional.of(new PdfImage(document, src, classe));
		}
		return Optional.empty();
	}

}
