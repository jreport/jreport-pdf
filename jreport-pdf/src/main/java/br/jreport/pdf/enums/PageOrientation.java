package br.jreport.pdf.enums;

import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;

public enum PageOrientation {
	/**
	 * Paisagem
	 */
	LANDSCAPE(PageSize.A4.rotate()),

	/**
	 * Retrato
	 */
	PORTRAIT(PageSize.A4);

	private Rectangle value;

	private PageOrientation(Rectangle value) {
		this.value = value;
	}

	public Rectangle getValue() {
		return value;
	}
}
