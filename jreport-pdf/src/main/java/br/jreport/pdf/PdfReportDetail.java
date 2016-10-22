package br.jreport.pdf;

import java.util.function.BiConsumer;

import br.jreport.core.api.Detail;
import br.jreport.core.api.Report;
import br.jreport.core.api.TableRow;
import br.jreport.core.api.datasource.Datasource;

public class PdfReportDetail implements Detail {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Detail image(String src) {
		return null;
	}

	@Override
	public Detail text(String text) {
		return null;
	}

	@Override
	public <T, D extends Datasource<T>> Detail table(D dataSource) {
		return null;
	}

	@Override
	public <T, D extends Datasource<T>> Detail table(D dataSource, BiConsumer<T, TableRow> eachRow) {
		return null;
	}

	@Override
	public <T, D extends Datasource<T>> Detail list(D datasource) {
		return null;
	}

	@Override
	public Report buildDetail() {
		return null;
	}

}
