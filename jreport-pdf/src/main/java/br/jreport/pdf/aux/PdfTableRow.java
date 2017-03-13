package br.jreport.pdf.aux;

import br.jreport.core.api.aux.NewTableRow;
import br.jreport.core.api.datasource.NewDatasource;

public class PdfTableRow implements NewTableRow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int rowIndex;

	private boolean last;

	private boolean first;

	private boolean odd;

	private boolean even;

	public PdfTableRow(int rowIndex, boolean last, boolean first, boolean odd, boolean even) {
		super();
		this.rowIndex = rowIndex;
		this.last = last;
		this.first = first;
		this.odd = odd;
		this.even = even;
	}

	@Override
	public int rowIndex() {
		return this.rowIndex;
	}

	@Override
	public boolean isLast() {
		return this.last;
	}

	@Override
	public boolean isFirst() {
		return this.first;
	}

	@Override
	public boolean isOdd() {
		return this.odd;
	}

	@Override
	public boolean isEven() {
		return this.even;
	}

	@Override
	public <T, D extends NewDatasource<T>> NewTableRow list(D datasource) {
		return null;
	}

}
