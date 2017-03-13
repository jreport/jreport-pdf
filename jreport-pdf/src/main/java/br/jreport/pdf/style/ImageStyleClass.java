package br.jreport.pdf.style;

import java.util.Map;
import java.util.Optional;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.itextpdf.layout.property.HorizontalAlignment;

import br.jreport.core.impl.NewImageStyleIml;
import br.jreport.core.impl.Style;

public class ImageStyleClass extends NewImageStyleIml {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7251324692135965333L;

	private HorizontalAlignment align;

	private Float width;

	private Float height;

	private Float marginTop;

	private Float marginLeft;

	private Float marginRight;

	private Float marginBottom;

	public static Optional<ImageStyleClass> of(Style style) {
		if (!Strings.isNullOrEmpty(style.getClasse())) {
			return Optional.of(new ImageStyleClass(style));
		}
		return Optional.of(new ImageStyleClass(Style.of("").get()));
	}

	protected ImageStyleClass(Style classe) {
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
		setHorizontalAlignment(map.get("horizontal-alignment"));
		setWidth(map.get("width"));
		setHeight(map.get("height"));
		setMarginTop(map.get("margin-top"));
		setMarginLeft(map.get("margin-left"));
		setMarginRight(map.get("margin-right"));
		setMarginBottom(map.get("margin-bottom"));
	}

	public void setWidth(String width) {
		if (!Strings.isNullOrEmpty(width)) {
			this.width = Float.valueOf(width.trim().toUpperCase());
		}
	}

	public void setHeight(String height) {
		if (!Strings.isNullOrEmpty(height)) {
			this.height = Float.valueOf(height.trim().toUpperCase());
		}
	}

	public void setHorizontalAlignment(String textAlign) {
		if (!Strings.isNullOrEmpty(textAlign)) {
			this.align = HorizontalAlignment.valueOf(textAlign.trim().toUpperCase());
		}
	}

	public void setMarginTop(String marginTop) {
		if (!Strings.isNullOrEmpty(marginTop)) {
			this.marginTop = Float.valueOf(marginTop.trim().toUpperCase());
		}
	}

	public void setMarginLeft(String marginLeft) {
		if (!Strings.isNullOrEmpty(marginLeft)) {
			this.marginLeft = Float.valueOf(marginLeft.trim().toUpperCase());
		}
	}

	public void setMarginRight(String marginRight) {
		if (!Strings.isNullOrEmpty(marginRight)) {
			this.marginRight = Float.valueOf(marginRight.trim().toUpperCase());
		}
	}

	public void setMarginBottom(String marginBottom) {
		if (!Strings.isNullOrEmpty(marginBottom)) {
			this.marginBottom = Float.valueOf(marginBottom.trim().toUpperCase());
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

	public Float getMarginTop() {
		return marginTop;
	}

	public Float getMarginLeft() {
		return marginLeft;
	}

	public Float getMarginRight() {
		return marginRight;
	}

	public Float getMarginBottom() {
		return marginBottom;
	}

}
