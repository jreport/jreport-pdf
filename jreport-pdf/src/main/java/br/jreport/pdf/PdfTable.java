package br.jreport.pdf;

import java.util.function.BiConsumer;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;

import br.jreport.core.api.Table;
import br.jreport.core.api.TableRow;
import br.jreport.core.api.adapter.TableAdapter;

public class PdfTable<T> implements Table<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Document document;

	private TableAdapter<T> tableAdapter;

	public PdfTable(Document document, TableAdapter<T> tableAdapter) {
		super();
		this.document = document;
		this.tableAdapter = tableAdapter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.jreport.core.api.Table#build(br.jreport.core.api.datasource.
	 * Datasource, br.jreport.core.api.adapter.TableAdapter)
	 */
	@Override
	public void build() {
		if (tableAdapter.numColumns() > 0) {
			PdfPTable pdfPTable = new PdfPTable(tableAdapter.numColumns());
			tableAdapter.getHeaders().stream().forEach(th -> pdfPTable.addCell(th.getText()));
			int numColumns = tableAdapter.numColumns();
			int numRows = tableAdapter.getDatasource().getList().size();
			for (int row = 0; row < numRows; row++) {
				T item = tableAdapter.getDatasource().getList().get(row);
				for (int column = 0; column < numColumns; column++) {
					tableAdapter.getColumn(item, column).ifPresent(td -> pdfPTable.addCell(td.getText()));
				}
			}
			PdfReport.addToDocument(document, pdfPTable);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.jreport.core.api.Table#table(br.jreport.core.api.datasource.
	 * Datasource, br.jreport.core.api.adapter.TableAdapter,
	 * java.util.function.BiConsumer)
	 */
	@Override
	public void build(BiConsumer<T, TableRow> eachRow) {
		if (tableAdapter.numColumns() > 0) {
			PdfPTable pdfPTable = new PdfPTable(tableAdapter.numColumns());
			tableAdapter.getHeaders().stream().forEach(th -> pdfPTable.addCell(th.getText()));
			int qtdColumns = tableAdapter.numColumns();
			int qtdRows = tableAdapter.getDatasource().getList().size();
			// Iteração por linha
			for (int row = 0; row < qtdRows; row++) {
				T item = tableAdapter.getDatasource().getList().get(row);
				boolean last = row == qtdRows - 1;
				boolean first = row == 0;
				boolean odd = row % 2 == 1;
				boolean even = row % 2 == 0;
				TableRow tableRow = new PdfTableRow(row, last, first, odd, even);
				eachRow.accept(item, tableRow);
				// Iteração por colunas
				for (int column = 0; column < qtdColumns; column++) {
					tableAdapter.getColumn(item, column).ifPresent(td -> pdfPTable.addCell(td.getText()));
				}
			}
			// PdfReport.addToDocument(document, pdfPTable);
			PdfReport.addToDocument(document, pdfPTable);
		}
	}

}
