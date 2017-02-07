package br.jreport.pdf;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.PdfWriter;

import br.jreport.core.StyleClass;
import br.jreport.core.api.Detail;
import br.jreport.core.api.Footer;
import br.jreport.core.api.Report;
import br.jreport.core.api.ReportOutputData;
import br.jreport.core.api.Title;
import br.jreport.pdf.aux.PdfReportOutputData;
import br.jreport.pdf.regions.PdfRegionDetail;
import br.jreport.pdf.regions.PdfRegionTitle;

public class PdfReport implements Report {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Document document = new Document();

	private PdfWriter pdfWriter;

	private Title title;

	private Detail detail;

	private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

	private StyleClass styleClass;

	public PdfReport() throws FileNotFoundException, DocumentException {
		super();
		this.pdfWriter = PdfWriter.getInstance(document, outputStream);
		this.document.open();
		this.title = new PdfRegionTitle(this.document, this);
		this.detail = new PdfRegionDetail(this.document, this);
	}

	@Override
	public Title title() {
		return this.title;
	}

	@Override
	public Detail detail() {
		return this.detail;
	}

	@Override
	public Footer footer() {
		return null;
	}

	@Override
	public ReportOutputData buildReport() {
		document.close();
		return new PdfReportOutputData(outputStream);
	}

	public static void addToDocument(Document document, Element element) {
		try {
			document.add(element);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

}
