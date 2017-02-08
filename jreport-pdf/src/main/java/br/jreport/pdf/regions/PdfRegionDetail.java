package br.jreport.pdf.regions;

import java.util.function.BiConsumer;

import com.lowagie.text.Document;

import br.jreport.core.api.Detail;
import br.jreport.core.api.Report;
import br.jreport.core.api.TableRow;
import br.jreport.core.api.datasource.Datasource;
import br.jreport.core.api.property.TableProperty;
import br.jreport.pdf.implementations.PdfImage;
import br.jreport.pdf.implementations.PdfNewLine;
import br.jreport.pdf.implementations.PdfNewPage;
import br.jreport.pdf.implementations.PdfNewSeparator;
import br.jreport.pdf.implementations.PdfTable;
import br.jreport.pdf.implementations.PdfText;

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
	public Detail addImage(String src) {
		PdfImage.of(document, src).ifPresent(image -> image.build());
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.jreport.core.api.Detail#text(java.lang.String)
	 */
	@Override
	public Detail addText(String text) {
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
	public <T, A extends TableProperty<T>> Detail addTable(A tableAdapter) {
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
	public <T, A extends TableProperty<T>> Detail addTable(A tableAdapter, BiConsumer<T, TableRow> eachRow) {
		PdfTable.of(document, tableAdapter).ifPresent(table -> table.build(eachRow));
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.jreport.core.api.Detail#newLine()
	 */
	@Override
	public Detail addNewLine() {
		PdfNewLine.of(document).ifPresent(newLine -> newLine.build());
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.jreport.core.api.Detail#list(br.jreport.core.api.datasource.
	 * Datasource)
	 */
	@Override
	public <T, D extends Datasource<T>> Detail addList(D datasource) {
		return this;
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

	@Override
	public Detail addSeparator() {
		PdfNewSeparator.of(document).ifPresent(newSeparator -> newSeparator.build());
		return this;
	}

	@Override
	public Detail addNewPage() {
		PdfNewPage.of(document).ifPresent(newPage -> newPage.build());
		return this;
	}

}
