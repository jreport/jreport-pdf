package br.jreport.pdf.style;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;

import br.jreport.core.api.TextStyle;
import br.jreport.pdf.enums.FontStyle;
import br.jreport.pdf.enums.TextAlign;
import br.jreport.pdf.enums.TextDecoration;

public class TextStyleClass implements TextStyle<TextStyleClass> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Text **/

	private Color color = Color.BLACK;

	private TextAlign textAlign = TextAlign.JUSTIFIED;

	private TextDecoration textDecoration = TextDecoration.NONE;

	private float textIndent = 0;

	private float textMarginLeft = 0;

	/** Font **/

	private float fontSize = 9;

	private FontStyle fontStyle = FontStyle.NORMAL;

	public static void main(String[] args) {
		TextStyleClass a = new TextStyleClass("text-align: center ; font-size:1.5; font-style:italic;  "
				+ "color:#005000; text-decoration: underline; background-color: #FFFFFF; text-indent:10; indentation-left: 10");
		System.out.println(a);
	}

	private TextStyleClass(String style) {
		try {
			if (style.contains(":")) {
				Map<String, String> map = Splitter.on(";").trimResults().omitEmptyStrings().withKeyValueSeparator(":").split(style);
				setColor(map.get("color"));
				setTextAlign(map.get("text-align"));
				setTextDecoration(map.get("text-decoration"));
				setTextIndent(map.get("text-indent"));
				setTextMarginLeft(map.get("margin-left"));
				setFontSize(map.get("font-size"));
				setFontStyle(map.get("font-style"), map.get("font-weight"));
			} else {
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

	public void setFontStyle(String fontStyle, String fontWeight) {
		if (fontStyle != null) {
			fontStyle = fontStyle.trim().toUpperCase().equals("NORMAL") ? null : fontStyle;
		}
		if (fontWeight != null) {
			fontWeight = fontWeight.trim().toUpperCase().equals("NORMAL") ? null : fontWeight;
		}
		if (fontStyle != null && fontWeight == null) {
			this.fontStyle = FontStyle.valueOf(fontStyle.trim().toUpperCase());
		} else if (fontStyle == null && fontWeight != null) {
			this.fontStyle = FontStyle.valueOf(fontWeight.trim().toUpperCase());
		} else if (fontStyle != null && fontWeight != null) {
			if (fontStyle.trim().toUpperCase().equals("ITALIC") && fontWeight.trim().toUpperCase().equals("BOLD")) {
				this.fontStyle = FontStyle.BOLDITALIC;
			}
		}
	}

	@Override
	public void setTextAlign(String textAlign) {
		if (textAlign != null) {
			this.textAlign = TextAlign.valueOf(textAlign.trim().toUpperCase());
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
				this.color = Color.decode(fontColor.trim().toUpperCase());
			} else {
				try {
					Field field = Color.class.getField(fontColor.trim().toLowerCase());
					this.color = (Color) field.get(null);
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

	public static Optional<TextStyleClass> of(String style) {
		if (!Strings.isNullOrEmpty(style)) {
			return Optional.of(new TextStyleClass(style));
		}
		return Optional.empty();
	}

	@Override
	public TextStyleClass build() {
		return this;
	}

	@Override
	public void setFontStyle(String fontStyle) {
		// TODO Auto-generated method stub

	}

	public TextAlign getTextAlign() {
		return textAlign;
	}

	public void setTextAlign(TextAlign textAlign) {
		this.textAlign = textAlign;
	}

	public FontStyle getFontStyle() {
		return fontStyle;
	}

	public void setFontStyle(FontStyle fontStyle) {
		this.fontStyle = fontStyle;
	}

	public float getFontSize() {
		return fontSize;
	}

	public void setFontSize(float fontSize) {
		this.fontSize = fontSize;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color fontColor) {
		this.color = fontColor;
	}

	public TextDecoration getTextDecoration() {
		return textDecoration;
	}

	public void setTextDecoration(TextDecoration textDecoration) {
		this.textDecoration = textDecoration;
	}

	public float getTextIndent() {
		return textIndent;
	}

	public void setTextIndent(float indent) {
		this.textIndent = indent;
	}

	public float getMarginLeft() {
		return textMarginLeft;
	}

	public void setMarginLeft(float marginLeft) {
		this.textMarginLeft = marginLeft;
	}

}
