package br.jreport.pdf;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.BlockElement;
import com.itextpdf.layout.element.Image;

import br.jreport.core.api.NewReport;
import br.jreport.core.api.aux.NewReportOutputData;
import br.jreport.core.api.regions.NewDetail;
import br.jreport.core.api.regions.NewFooter;
import br.jreport.core.api.regions.NewTitle;
import br.jreport.pdf.aux.PdfReportOutputData;
import br.jreport.pdf.regions.PdfRegionDetail;
import br.jreport.pdf.regions.PdfRegionTitle;

public class PdfReport implements NewReport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Document document;

	private NewTitle title;

	private NewDetail detail;

	private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

	public PdfReport() throws FileNotFoundException {
		super();
		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(outputStream));
		document = new Document(pdfDoc);
		this.title = new PdfRegionTitle(this.document, this);
		this.detail = new PdfRegionDetail(this.document, this);
	}

	@Override
	public NewTitle title() {
		return this.title;
	}

	@Override
	public NewDetail detail() {
		return this.detail;
	}

	@Override
	public NewFooter footer() {
		return null;
	}

	@Override
	public NewReportOutputData buildReport() {
		document.close();
		return new PdfReportOutputData(outputStream);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void addToDocument(Document document, BlockElement element) {
		document.add(element);
	}

	public static void addToDocument(Document document, Image element) {
		document.add(element);
	}

	public static void addToDocument(Document document, AreaBreak element) {
		document.add(element);
	}

}
