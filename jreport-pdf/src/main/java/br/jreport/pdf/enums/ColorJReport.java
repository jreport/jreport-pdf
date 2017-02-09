package br.jreport.pdf.enums;

import java.awt.Color;

public enum ColorJReport {
	//@formatter:off
	LIGHT_LIGHT_GRAY(Color.decode("#D2D2D2")), 
	LIGHT_GRAY(Color.LIGHT_GRAY), 
	BLACK(Color.BLACK), 
	DARK_GRAY(Color.DARK_GRAY),
	WHITE(Color.WHITE);
	//@formatter:on

	private Color color;

	private ColorJReport(Color border) {
		this.color = border;
	}

	public Color getColor() {
		return color;
	}

}
