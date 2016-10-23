package br.jreport.pdf;

import com.lowagie.text.Paragraph;

import br.jreport.core.api.Text;

public class PdfText implements Text {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String text;

	private Paragraph paragraph;

	public PdfText(Paragraph paragraph, String text) {
		super();
		this.text = text;
		this.paragraph = paragraph;
	}

	@Override
	public void build() {
		paragraph.add(text);
	}

}
