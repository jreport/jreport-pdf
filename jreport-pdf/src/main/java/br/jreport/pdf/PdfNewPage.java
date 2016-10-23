package br.jreport.pdf;

import com.lowagie.text.Document;

import br.jreport.core.api.NewPage;

public class PdfNewPage implements NewPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Document document;

	public PdfNewPage(Document document) {
		super();
		this.document = document;
	}

	@Override
	public void build() {
		this.document.newPage();
	}

}
