package br.jreport.pdf.enums;

public enum ImageAlign {
	LEFT(0), CENTER(1), RIGHT(2), JUSTIFIED(3), TOP(4), MIDDLE(5), BOTTOM(6);

	private int value;

	private ImageAlign(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
