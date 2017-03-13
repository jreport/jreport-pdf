package br.jreport.pdf.style;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.layout.property.TextAlignment;

import br.jreport.core.impl.NewTextStyleIml;
import br.jreport.core.impl.Style;
import br.jreport.pdf.enums.TextDecoration;

public class TextStyleClass extends NewTextStyleIml {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Text **/

	private Color color;
	private TextAlignment textAlign;
	private TextAlignment textAlignNumber;
	private TextDecoration textDecoration;
	private float textIndent;
	private float textMarginLeft;
	private float fontSize;
	private boolean fontStyle;
	private boolean fontBold;

	public static Optional<TextStyleClass> of(Style style) {
		// if (!Strings.isNullOrEmpty(style)) {
		return Optional.of(new TextStyleClass(style));
		// }
		// return Optional.of(new TextStyleClass(""));
	}

	protected TextStyleClass() {
		// TODO Auto-generated constructor stub
	}

	protected TextStyleClass(Style classe) {
		super(classe);
		try {
			Map<String, String> map = StyleMap.getClassStyle(classe.getClasse());
			setAtributtes(map);

			if (classe.getInline().contains(":")) {
				Map<String, String> mapInline = Splitter.on(";").trimResults().omitEmptyStrings().withKeyValueSeparator(":")
						.split(classe.getInline());
				setAtributtes(mapInline);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setAtributtes(Map<String, String> map) {
		setColor(map.get("color"));
		setTextAlign(map.get("text-align"));
		setTextAlignNumber(map.get("text-align-number"));
		setTextDecoration(map.get("text-decoration"));
		setTextIndent(map.get("text-indent"));
		setTextMarginLeft(map.get("margin-left"));
		setFontSize(map.get("font-size"));
		setFontStyle(map.get("font-style"));
		setFontWeight(map.get("font-weight"));
	}

	@Override
	public void setTextMarginLeft(String marginLeft) {
		if (!Strings.isNullOrEmpty(marginLeft)) {
			try {
				this.textMarginLeft = Float.valueOf(marginLeft.trim());
			} catch (NumberFormatException e) {
				throw new NumberFormatException("Elemento inválido para margin-left, valor: " + marginLeft);
			}
		}
	}

	@Override
	public void setTextIndent(String textIndent) {
		if (!Strings.isNullOrEmpty(textIndent)) {
			try {
				this.textIndent = Float.valueOf(textIndent.trim());
			} catch (NumberFormatException e) {
				throw new NumberFormatException("Elemento inválido para textIndent, valor: " + textIndent);
			}
		}
	}

	@Override
	public void setTextDecoration(String textDecoration) {
		if (!Strings.isNullOrEmpty(textDecoration)) {
			this.textDecoration = TextDecoration.valueOf(textDecoration.trim().toUpperCase());
		}
	}

	@Override
	public void setFontStyle(String fontStyle) {
		if (!Strings.isNullOrEmpty(fontStyle)) {
			fontStyle = fontStyle.trim().toUpperCase().equals("NORMAL") ? "normal" : fontStyle;
			if (fontStyle.equals("italic")) {
				this.fontStyle = true;
			} else {
				this.fontStyle = false;
			}
		}
	}

	@Override
	public void setFontWeight(String fontWeight) {
		if (!Strings.isNullOrEmpty(fontWeight)) {
			fontWeight = fontWeight.trim().toUpperCase().equals("NORMAL") ? "normal" : fontWeight;
			if (fontWeight.equals("bold")) {
				this.fontBold = true;
			} else {
				this.fontBold = false;
			}
		}
	}

	@Override
	public void setTextAlign(String textAlign) {
		if (!Strings.isNullOrEmpty(textAlign)) {
			this.textAlign = TextAlignment.valueOf(textAlign.trim().toUpperCase());
		}
	}

	@Override
	public void setTextAlignNumber(String textAlign) {
		if (!Strings.isNullOrEmpty(textAlign)) {
			this.textAlignNumber = TextAlignment.valueOf(textAlign.trim().toUpperCase());
		}
	}

	@Override
	public void setFontSize(String fontSize) {
		if (!Strings.isNullOrEmpty(fontSize)) {
			try {
				this.fontSize = Float.valueOf(fontSize.trim());
			} catch (NumberFormatException e) {
				throw new NumberFormatException("Elemento inválido para fontSize, valor: " + fontSize);
			}
		}
	}

	@Override
	public void setColor(String fontColor) {
		if (!Strings.isNullOrEmpty(fontColor)) {
			if (fontColor.trim().startsWith("#")) {
				java.awt.Color parse = java.awt.Color.decode(fontColor.trim().toUpperCase());
				this.color = new DeviceRgb(parse.getRed(), parse.getGreen(), parse.getBlue());
			} else {
				try {
					Field field = java.awt.Color.class.getField(fontColor.trim().toLowerCase());
					java.awt.Color parse = (java.awt.Color) field.get(null);
					this.color = new DeviceRgb(parse.getRed(), parse.getGreen(), parse.getBlue());
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public String toString() {
		return "TextStyleClass [fontColor=" + color + ", textAlign=" + textAlign + ", textDecoration=" + textDecoration + ", indent="
				+ textIndent + ", marginLeft=" + textMarginLeft + ", fontSize=" + fontSize + ", fontStyle=" + fontStyle + "]";
	}

	public Color getColor() {
		return color;
	}

	public TextAlignment getTextAlign() {
		return textAlign;
	}

	public TextDecoration getTextDecoration() {
		return textDecoration;
	}

	public float getTextIndent() {
		return textIndent;
	}

	public float getTextMarginLeft() {
		return textMarginLeft;
	}

	public float getFontSize() {
		return fontSize;
	}

	public boolean isFontStyle() {
		return fontStyle;
	}

	public boolean isFontBold() {
		return fontBold;
	}

	public TextAlignment getTextAlignNumber() {
		return textAlignNumber;
	}

}
