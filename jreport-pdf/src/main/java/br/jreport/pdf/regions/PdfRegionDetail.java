package br.jreport.pdf.regions;

import java.util.function.BiConsumer;

import com.itextpdf.layout.Document;

import br.jreport.core.api.NewColspanBody;
import br.jreport.core.api.NewDetail;
import br.jreport.core.api.NewReport;
import br.jreport.core.api.NewTableRow;
import br.jreport.core.api.datasource.NewDatasource;
import br.jreport.core.api.property.NewTableProperty;
import br.jreport.pdf.implementations.PdfColspanBody;
import br.jreport.pdf.implementations.PdfColspanLine;
import br.jreport.pdf.implementations.PdfImage;
import br.jreport.pdf.implementations.PdfNewLine;
import br.jreport.pdf.implementations.PdfNewPage;
import br.jreport.pdf.implementations.PdfNewSeparator;
import br.jreport.pdf.implementations.PdfTable;
import br.jreport.pdf.implementations.PdfText;

public class PdfRegionDetail implements NewDetail {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Document document;

	private NewReport report;

	public PdfRegionDetail(Document document, NewReport report) {
		super();
		this.document = document;
		this.report = report;
	}

	@Override
	public NewDetail addImage(String src) {
		PdfImage.of(document, src).ifPresent(image -> image.build());
		return this;
	}

	@Override
	public NewDetail addImage(String src, String classe) {
		PdfImage.of(document, src, classe).ifPresent(image -> image.build());
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.jreport.core.api.Detail#text(java.lang.String)
	 */
	@Override
	public NewDetail addText(String text) {
		PdfText.of(document, text).ifPresent(txt -> txt.build());
		return this;
	}

	@Override
	public NewDetail addText(String text, String classe) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.jreport.core.api.Detail#newLine()
	 */
	@Override
	public NewDetail addNewLine() {
		PdfNewLine.of(document).ifPresent(newLine -> newLine.build());
		return this;
	}

	@Override
	public NewDetail addSeparator() {
		PdfNewSeparator.of(document).ifPresent(newSeparator -> newSeparator.build());
		return this;
	}

	@Override
	public NewDetail addNewPage() {
		PdfNewPage.of(document).ifPresent(newPage -> newPage.build());
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.jreport.core.api.Detail#table(br.jreport.core.api.datasource.
	 * Datasource, br.jreport.core.api.adapter.TableAdapter)
	 */
	@Override
	public <T, A extends NewTableProperty<T>> NewDetail addTable(A tableAdapter) {
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
	public <T, A extends NewTableProperty<T>> NewDetail addTable(A tableAdapter, BiConsumer<T, NewTableRow> eachRow) {
		PdfTable.of(document, tableAdapter).ifPresent(table -> table.build(eachRow));
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.jreport.core.api.Detail#list(br.jreport.core.api.datasource.
	 * Datasource)
	 */
	@Override
	public <T, D extends NewDatasource<T>> NewDetail addList(D datasource) {
		return this;
	}

	@Override
	// TODO não gostei muito dessa sintaxe podemos verificar melhor maneira de
	// passar o document.
	public NewDetail addColspanline(NewColspanBody tableProperty) {
		PdfColspanLine.of(document, (PdfColspanBody) tableProperty).ifPresent(table -> table.build());
		;
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.jreport.core.api.Detail#buildDetail()
	 */
	@Override
	public NewReport buildDetail() {
		return this.report;
	}

	public <T, A extends NewTableProperty<T>> NewDetail addTable(A tableProperty, String classe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T, A extends NewTableProperty<T>> NewDetail addTable(A tableProperty, BiConsumer<T, NewTableRow> eachRow, String classe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T, D extends NewDatasource<T>> NewDetail addList(D datasource, String classe) {
		// TODO Auto-generated method stub
		return null;
	}

}
