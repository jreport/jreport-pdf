package br.jreport.pdf.implementations;

import java.util.Optional;

import com.lowagie.text.Document;

import br.jreport.core.api.NewPage;
import br.jreport.pdf.helper.DocumentHelper;

public class PdfNewPage implements NewPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Document document;

	private PdfNewPage() {
		super();
	}

	private PdfNewPage(Document document) {
		super();
		this.document = document;
	}

	@Override
	public void build() {
		DocumentHelper.newPage(this.document);
	}

	public static Optional<PdfNewPage> of(Document document) {
		if (document != null) {
			return Optional.of(new PdfNewPage(document));
		}
		return Optional.empty();
	}

}
