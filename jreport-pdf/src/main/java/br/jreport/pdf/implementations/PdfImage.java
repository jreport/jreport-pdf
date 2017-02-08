package br.jreport.pdf.implementations;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.Image;

import br.jreport.core.api.NewImage;
import br.jreport.pdf.PdfReport;

public class PdfImage implements NewImage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String src;

	private Document document;

	private PdfImage() {
		super();
	}

	private PdfImage(Document document, String src) {
		super();
		this.document = document;
		this.src = src;
	}

	@Override
	public void build() {
		Image image = null;
		try {
			URL imageURL = getClass().getClassLoader().getResource(src);
			image = Image.getInstance(imageURL);
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		PdfReport.addToDocument(document, image);
	}

	public static Optional<PdfImage> of(Document document, String src) {
		if (document != null && src != null) {
			return Optional.of(new PdfImage(document, src));
		}
		return Optional.empty();
	}

}
