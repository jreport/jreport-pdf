package br.jreport.pdf.implementations;

import java.util.Optional;
import java.util.function.BiConsumer;

import com.itextpdf.layout.Document;

import br.jreport.core.api.aux.NewTableRow;
import br.jreport.core.api.interfaces.NewTable;
import br.jreport.core.api.property.NewTableProperty;
import br.jreport.core.impl.Style;
import br.jreport.pdf.PdfReport;
import br.jreport.pdf.helper.DocumentHelper;

public class PdfTable<T> implements NewTable<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Document document;

	private Optional<Style> classe = null;

	private NewTableProperty<T> tableAdapter;

	private PdfTable() {
		super();
		// TODO Auto-generated constructor stub
	}

	private PdfTable(Document document, NewTableProperty<T> tableAdapter) {
		super();
		this.document = document;
		this.tableAdapter = tableAdapter;
	}

	private PdfTable(Document document, NewTableProperty<T> tableAdapter, Optional<Style> classe) {
		super();
		this.document = document;
		this.tableAdapter = tableAdapter;
		this.classe = classe;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.jreport.core.api.Table#build(br.jreport.core.api.datasource.
	 * Datasource, br.jreport.core.api.adapter.TableAdapter)
	 */
	@Override
	public void build() {
		PdfReport.addToDocument(document, DocumentHelper.createTable(tableAdapter, classe));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.jreport.core.api.Table#table(br.jreport.core.api.datasource.
	 * Datasource, br.jreport.core.api.adapter.TableAdapter,
	 * java.util.function.BiConsumer)
	 */
	@Override
	public void build(BiConsumer<T, NewTableRow> eachRow) {
		PdfReport.addToDocument(document, DocumentHelper.createTable(tableAdapter, eachRow, classe));
	}

	public static <T> Optional<NewTable<T>> of(Document document, NewTableProperty<T> tableAdapter) {
		if (document != null && tableAdapter != null) {
			return Optional.of(new PdfTable<T>(document, tableAdapter));
		}
		return Optional.empty();
	}

	public static <T> Optional<NewTable<T>> of(Document document, NewTableProperty<T> tableAdapter, Optional<Style> style) {
		if (document != null && tableAdapter != null && style.isPresent()) {
			return Optional.of(new PdfTable<T>(document, tableAdapter, style));
		}
		return Optional.empty();
	}

}
