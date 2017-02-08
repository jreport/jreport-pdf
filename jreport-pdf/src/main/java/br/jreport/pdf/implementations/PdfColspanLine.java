package br.jreport.pdf.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

import br.jreport.core.api.NewColspanLine;
import br.jreport.pdf.PdfReport;

//TODO Fazer ajustes nessa implementaçãpo
public class PdfColspanLine implements NewColspanLine {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Document document;

	private List<PdfPCell> cells = new ArrayList<PdfPCell>();

	private Integer headersColspan = 1;

	private PdfColspanLine() {
		super();
		// TODO Auto-generated constructor stub
	}

	private PdfColspanLine(Document document) {
		super();
		this.document = document;
	}

	public PdfColspanLine addHeadersColspan(Integer headers) {
		headersColspan = headers;
		return this;
	}

	public PdfColspanLine addCell(String text) {
		Paragraph paragraph = new Paragraph();
		paragraph.add(text);
		PdfPCell cell = new PdfPCell();
		cell.addElement(paragraph);
		cells.add(cell);
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.jreport.core.api.Table#build(br.jreport.core.api.datasource.
	 * Datasource, br.jreport.core.api.adapter.TableAdapter)
	 */
	@Override
	public void build() {
		if (headersColspan > 0) {
			PdfPTable pdfPTable = new PdfPTable(headersColspan);
			int numRows = cells.size();
			for (int row = 0; row < numRows; row++) {
				PdfPCell item = cells.get(row);
				pdfPTable.addCell(item);
			}
			PdfReport.addToDocument(document, pdfPTable);
		}
	}

	public static <T> Optional<NewColspanLine> of(Document document) {
		if (document != null) {
			return Optional.of(new PdfColspanLine(document));
		}
		return Optional.empty();

	}

}
