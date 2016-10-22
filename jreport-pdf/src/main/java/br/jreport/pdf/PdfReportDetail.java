package br.jreport.pdf;

import java.util.function.BiConsumer;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;

import br.jreport.core.api.Detail;
import br.jreport.core.api.Report;
import br.jreport.core.api.TableRow;
import br.jreport.core.api.adapter.TableAdapter;
import br.jreport.core.api.datasource.Datasource;

public class PdfReportDetail implements Detail {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Document document;

	private Report report;

	public PdfReportDetail(Document document, Report report) {
		super();
		this.document = document;
		this.report = report;
	}

	@Override
	public Detail image(String src) {
		return null;
	}

	@Override
	public Detail text(String text) {
		return null;
	}

	@Override
	public <T, A extends TableAdapter<T>, D extends Datasource<T>> Detail table(D dataSource, A tableAdapter) {
		if (tableAdapter.getHeaders() != null && tableAdapter.getHeaders().size() > 0) {
			PdfPTable pdfPTable = new PdfPTable(tableAdapter.getHeaders().size());
			tableAdapter.getHeaders().stream().forEach(pdfPTable::addCell);
			for (int i = 0; i < dataSource.get().size(); i++) {
				T item = dataSource.get().get(i);
				tableAdapter.getColumnValue(item, i).ifPresent(pdfPTable::addCell);
			}
			PdfReport.addToDocument(document, pdfPTable);
		}
		return this;
	}

	@Override
	public <T, A extends TableAdapter<T>, D extends Datasource<T>> Detail table(D dataSource, A tableAdapter,
			BiConsumer<T, TableRow> eachRow) {
		return null;
	}

	@Override
	public <T, D extends Datasource<T>> Detail list(D datasource) {
		return null;
	}

	@Override
	public Report buildDetail() {
		return this.report;
	}

}
