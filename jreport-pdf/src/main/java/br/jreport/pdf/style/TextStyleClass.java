package br.jreport.pdf.style;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.layout.property.TextAlignment;

import br.jreport.core.api.NewTextStyle;
import br.jreport.pdf.enums.TextDecoration;

public class TextStyleClass implements NewTextStyle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Text **/

	private Color color = Color.BLACK; // ok

	private TextAlignment textAlign = TextAlignment.JUSTIFIED; // ok

	private TextDecoration textDecoration = TextDecoration.NONE; // ok

	private float textIndent = 0; // ok

	private float textMarginLeft = 0; // ok

	/** Font **/

	private float fontSize = 9; // ok

	private boolean fontStyle = false; // ok

	private boolean fontBold = false; // ok

	public static void main(String[] args) {
		TextStyleClass a = new TextStyleClass("text-align: center ; font-size:1.5; font-style:italic;  "
				+ "color:#005000; text-decoration: underline; background-color: #FFFFFF; text-indent:10; indentation-left: 10");
		System.out.println(a);
	}
	
	public static Optional<TextStyleClass> of(String style) {
		if (!Strings.isNullOrEmpty(style)) {
			return Optional.of(new TextStyleClass(style));
		}
		return Optional.of(new TextStyleClass(""));
	}

	protected TextStyleClass(String style) {
		try {
			if (style.contains(":")) {
				Map<String, String> map = Splitter.on(";").trimResults().omitEmptyStrings().withKeyValueSeparator(":").split(style);
				setColor(map.get("color"));
				setTextAlign(map.get("text-align"));
				setTextDecoration(map.get("text-decoration"));
				setTextIndent(map.get("text-indent"));
				setTextMarginLeft(map.get("margin-left"));
				setFontSize(map.get("font-size"));
				setFontStyle(map.get("font-style"));
				setFontWeight(map.get("font-weight"));
			} else if (!style.isEmpty()) {
				throw new Exception("formato css inválido, chave e valor separados por ':' e elementos separados por ';' ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setTextMarginLeft(String marginLeft) {
		if (marginLeft != null) {
			try {
				this.textMarginLeft = Float.valueOf(marginLeft.trim());
			} catch (NumberFormatException e) {
				throw new NumberFormatException("Elemento inválido para margin-left, valor: " + marginLeft);
			}
		}
	}

	@Override
	public void setTextIndent(String textIndent) {
		if (textIndent != null) {
			try {
				this.textIndent = Float.valueOf(textIndent.trim());
			} catch (NumberFormatException e) {
				throw new NumberFormatException("Elemento inválido para textIndent, valor: " + textIndent);
			}
		}
	}

	@Override
	public void setTextDecoration(String textDecoration) {
		if (textDecoration != null) {
			this.textDecoration = TextDecoration.valueOf(textDecoration.trim().toUpperCase());
		}
	}

	@Override
	public void setFontStyle(String fontStyle) {
		if (fontStyle != null) {
			fontStyle = fontStyle.trim().toUpperCase().equals("NORMAL") ? null : fontStyle;
		}
		if (fontStyle != null) {
			this.fontStyle = true;
		}
	}

	@Override
	public void setFontWeight(String fontWeight) {
		if (fontWeight != null) {
			fontWeight = fontWeight.trim().toUpperCase().equals("NORMAL") ? null : fontWeight;
		}
		if (fontWeight != null) {
			this.fontStyle = true;
		}
	}

	@Override
	public void setTextAlign(String textAlign) {
		if (textAlign != null) {
			this.textAlign = TextAlignment.valueOf(textAlign.trim().toUpperCase());
		}
	}

	@Override
	public void setFontSize(String fontSize) {
		if (fontSize != null) {
			try {
				this.fontSize = Float.valueOf(fontSize.trim());
			} catch (NumberFormatException e) {
				throw new NumberFormatException("Elemento inválido para fontSize, valor: " + fontSize);
			}
		}
	}

	@Override
	public void setColor(String fontColor) {
		if (fontColor != null) {
			if (fontColor.trim().startsWith("#")) {
				java.awt.Color parse = java.awt.Color.decode(fontColor.trim().toUpperCase());
				this.color = new DeviceRgb(parse.getRed(), parse.getGreen(), parse.getBlue());
			} else {
				try {
					Field field = Color.class.getField(fontColor.trim().toLowerCase());
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

}
