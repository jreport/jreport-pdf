package br.jreport.pdf.style;

import java.util.Map;
import java.util.Optional;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.itextpdf.layout.property.HorizontalAlignment;

import br.jreport.core.api.NewImageStyle;

public class ImageStyleClass implements NewImageStyle {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7251324692135965333L;

	private HorizontalAlignment align = HorizontalAlignment.CENTER;

	private Float width;

	private Float height;

	public static Optional<ImageStyleClass> of(String style) {
		if (!Strings.isNullOrEmpty(style)) {
			return Optional.of(new ImageStyleClass(style));
		}
		return Optional.of(new ImageStyleClass(""));
	}

	protected ImageStyleClass(String style) {
		try {
			if (style.contains(":")) {
				Map<String, String> map = Splitter.on(";").trimResults().omitEmptyStrings().withKeyValueSeparator(":").split(style);
				setHorizontalAlignment(map.get("align"));
				setWidth(map.get("width"));
				setHeight(map.get("height"));
			} else if (!style.isEmpty()) {
				throw new Exception("formato css inválido, chave e valor separados por ':' e elementos separados por ';' ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setWidth(String width) {
		if (width != null) {
			this.width = Float.valueOf(width.trim().toUpperCase());
		}
	}

	public void setHeight(String height) {
		if (height != null) {
			this.height = Float.valueOf(height.trim().toUpperCase());
		}
	}

	public void setHorizontalAlignment(String textAlign) {
		if (textAlign != null) {
			this.align = HorizontalAlignment.valueOf(textAlign.trim().toUpperCase());
		}
	}

	public HorizontalAlignment getAlign() {
		return align;
	}

	public Float getWidth() {
		return width;
	}

	public Float getHeight() {
		return height;
	}

}
