package br.jreport.pdf.enums;

public enum TextDecoration {
	NONE(0f, 0f), UNDERLINE(0.1f, -2f), LINE_THROUGH(0.1f, 3f);

	private float thick;
	private float y;

	private TextDecoration(float thick, float y) {
		this.thick = thick;
		this.y = y;
	}

	public float getThick() {
		return thick;
	}

	public float getY() {
		return y;
	}

}
