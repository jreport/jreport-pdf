package br.jreport.pdf;

import java.util.function.BiConsumer;

import com.lowagie.text.Document;

import br.jreport.core.api.Detail;
import br.jreport.core.api.Report;
import br.jreport.core.api.TableRow;
import br.jreport.core.api.adapter.TableAdapter;
import br.jreport.core.api.datasource.Datasource;

public class PdfRegionDetail implements Detail {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Document document;

	private Report report;

	public PdfRegionDetail(Document document, Report report) {
		super();
		this.document = document;
		this.report = report;
	}

	@Override
	public Detail image(String src) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.jreport.core.api.Detail#text(java.lang.String)
	 */
	@Override
	public Detail text(String text) {
		PdfText.of(document, text).ifPresent(txt -> txt.build());
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.jreport.core.api.Detail#table(br.jreport.core.api.datasource.
	 * Datasource, br.jreport.core.api.adapter.TableAdapter)
	 */
	@Override
	public <T, A extends TableAdapter<T>> Detail table(A tableAdapter) {
		PdfTable.of(document, tableAdapter).ifPresent(table -> table.build());
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.jreport.core.api.Detail#table(br.jreport.core.api.datasource.
	 * Datasource, br.jreport.core.api.adapter.TableAdapter,
	 * java.util.function.BiConsumer)
	 */
	@Override
	public <T, A extends TableAdapter<T>> Detail table(A tableAdapter, BiConsumer<T, TableRow> eachRow) {
		PdfTable.of(document, tableAdapter).ifPresent(table -> table.build(eachRow));
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.jreport.core.api.Detail#newLine()
	 */
	@Override
	public Detail newLine() {
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.jreport.core.api.Detail#list(br.jreport.core.api.datasource.
	 * Datasource)
	 */
	@Override
	public <T, D extends Datasource<T>> Detail list(D datasource) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.jreport.core.api.Detail#buildDetail()
	 */
	@Override
	public Report buildDetail() {
		return this.report;
	}

}
