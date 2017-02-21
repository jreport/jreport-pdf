package br.jreport.pdf.implementations;

import java.util.Optional;
import java.util.function.BiConsumer;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;

import br.jreport.core.api.NewTable;
import br.jreport.core.api.NewTableRow;
import br.jreport.core.api.property.NewTableProperty;
import br.jreport.pdf.PdfReport;
import br.jreport.pdf.helper.DocumentHelper;

public class PdfTable<T> implements NewTable<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Document document;

	private String classe = null;

	private String headerClasse = null;

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

	private PdfTable(Document document, NewTableProperty<T> tableAdapter, String classe, String headerClasse) {
		super();
		this.document = document;
		this.tableAdapter = tableAdapter;
		this.classe = classe;
		this.headerClasse = headerClasse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.jreport.core.api.Table#build(br.jreport.core.api.datasource.
	 * Datasource, br.jreport.core.api.adapter.TableAdapter)
	 */
	@Override
	public void build() {
		Table table = DocumentHelper.createTable(tableAdapter, classe, headerClasse);
		PdfReport.addToDocument(document, table);
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
		Table table = DocumentHelper.createTable(tableAdapter, eachRow, classe, headerClasse);
		PdfReport.addToDocument(document, table);
	}

	public static <T> Optional<NewTable<T>> of(Document document, NewTableProperty<T> tableAdapter) {
		if (document != null && tableAdapter != null) {
			return Optional.of(new PdfTable<T>(document, tableAdapter));
		}
		return Optional.empty();
	}

	public static <T> Optional<NewTable<T>> of(Document document, NewTableProperty<T> tableAdapter, String classe, String headerClasse) {
		if (document != null && tableAdapter != null) {
			return Optional.of(new PdfTable<T>(document, tableAdapter, classe, headerClasse));
		}
		return Optional.empty();
	}

}
