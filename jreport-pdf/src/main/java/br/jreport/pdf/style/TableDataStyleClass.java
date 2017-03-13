package br.jreport.pdf.style;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import br.jreport.core.api.style.NewTextStyle;
import br.jreport.core.impl.NewTableStyleIml;
import br.jreport.core.impl.Style;

public class TableDataStyleClass extends NewTableStyleIml {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2725789315961746383L;

	private VerticalAlignment theadVerticalAlignment;
	private HorizontalAlignment theadHorizontalAlignment;
	private Float theadHeight;
	private Float theadWidth;
	private Color theadBorderColor;
	private Float theadBorderWidth;
	private Float theadBorderTopWidth;
	private Float theadBorderLeftWidth;
	private Float theadBorderRightWidth;
	private Float theadBorderBottomWidth;
	private int theadColspan;
	private int theadRolspan;
	private Color theadBackgroundCellColor;
	private TextStyleClass theadTextStyle = new TextStyleClass();

	private VerticalAlignment verticalAlignment;
	private HorizontalAlignment horizontalAlignment;
	private Float height;
	private Float width;
	private Color borderColor;
	private Float borderWidth;
	private Float borderTopWidth;
	private Float borderLeftWidth;
	private Float borderRightWidth;
	private Float borderBottomWidth;
	private int colspan;
	private int rolspan;
	private Color backgroundCellColor;
	private Color backgroundCellColorPar;
	private TextStyleClass textStyle = new TextStyleClass();

	public static Optional<TableDataStyleClass> of(Style style) {
		// if (!Strings.isNullOrEmpty(style)) {
		return Optional.of(new TableDataStyleClass(style));
		// }
		// return Optional.of(new TableDataStyleClass(""));
	}

	protected TableDataStyleClass(Style classe) {
		super(classe, new TextStyleClass(), new TextStyleClass());
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
		setTheadHorizontalAlignment(map.get("thead.horizontal-alignment"));
		setTheadVerticalAlignment(map.get("thead.vertical-alignment"));
		setTheadHeight(map.get("thead.height"));
		setTheadWidth(map.get("thead.width"));
		setTheadBorderColor(map.get("thead.border-color"));
		setTheadBorderTopWidth(map.get("thead.border-top-width"));
		setTheadBorderLeftWidth(map.get("thead.border-left-width"));
		setTheadBorderRightWidth(map.get("thead.border-right-width"));
		setTheadBorderBottomWidth(map.get("thead.border-bottom-width"));
		setTheadColspan(map.get("thead.colspan"));
		setTheadRolspan(map.get("thead.rolspan"));
		setTheadBackgroundCellColor(map.get("thead.background-color"));

		theadTextStyle = (TextStyleClass) super.getTheadNewTextStyle();
		theadTextStyle.setColor(map.get("thead.color"));
		theadTextStyle.setTextAlign(map.get("thead.text-align"));
		theadTextStyle.setTextAlignNumber(map.get("thead.text-align-number"));
		theadTextStyle.setTextDecoration(map.get("thead.text-decoration"));
		theadTextStyle.setTextIndent(map.get("thead.text-indent"));
		theadTextStyle.setTextMarginLeft(map.get("thead.margin-left"));
		theadTextStyle.setFontSize(map.get("thead.font-size"));
		theadTextStyle.setFontStyle(map.get("thead.font-style"));
		theadTextStyle.setFontWeight(map.get("thead.font-weight"));

		/**
		 * TBody
		 **/
		setHorizontalAlignment(map.get("horizontal-alignment"));
		setVerticalAlignment(map.get("vertical-alignment"));
		setHeight(map.get("height"));
		setWidth(map.get("width"));
		setBorderColor(map.get("border-color"));
		setBorderLeftWidth(map.get("border-left-width"));
		setBorderRightWidth(map.get("border-right-width"));
		setBorderBottomWidth(map.get("border-bottom-width"));
		setBorderTopWidth(map.get("border-top-width"));
		setColspan(map.get("colspan"));
		setRolspan(map.get("rolspan"));
		setBackgroundCellColor(map.get("background-color"));
		setBackgroundCellColorPar(map.get("background-color-par"));

		textStyle = (TextStyleClass) super.getNewTextStyle();
		textStyle.setColor(map.get("color"));
		textStyle.setTextAlign(map.get("text-align"));
		textStyle.setTextAlignNumber(map.get("text-align-number"));
		textStyle.setTextDecoration(map.get("text-decoration"));
		textStyle.setTextIndent(map.get("text-indent"));
		textStyle.setTextMarginLeft(map.get("margin-left"));
		textStyle.setFontSize(map.get("font-size"));
		textStyle.setFontStyle(map.get("font-style"));
		textStyle.setFontWeight(map.get("font-weight"));
	}

	public void setTheadWidth(String width) {
		if (!Strings.isNullOrEmpty(width)) {
			try {
				this.theadWidth = Float.valueOf(width.trim());
			} catch (NumberFormatException e) {
				throw new NumberFormatException("Elemento inválido para width, valor: " + width);
			}
		}
	}

	public void setTheadBackgroundCellColor(String backgroundCellColor) {
		if (!Strings.isNullOrEmpty(backgroundCellColor)) {
			if (backgroundCellColor.trim().startsWith("#")) {
				java.awt.Color parse = java.awt.Color.decode(backgroundCellColor.trim().toUpperCase());
				this.theadBackgroundCellColor = new DeviceRgb(parse.getRed(), parse.getGreen(), parse.getBlue());
			} else {
				try {
					Field field = java.awt.Color.class.getField(backgroundCellColor.trim().toLowerCase());
					java.awt.Color parse = (java.awt.Color) field.get(null);
					this.theadBackgroundCellColor = new DeviceRgb(parse.getRed(), parse.getGreen(), parse.getBlue());
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void setTheadColspan(String colspan) {
		if (!Strings.isNullOrEmpty(colspan)) {
			this.theadColspan = Integer.valueOf(colspan.trim());
		}
	}

	@Override
	public void setTheadRolspan(String rolspan) {
		if (!Strings.isNullOrEmpty(rolspan)) {
			this.theadRolspan = Integer.valueOf(rolspan.trim());
		}
	}

	public void setTheadBorderColor(String borderColor) {
		if (!Strings.isNullOrEmpty(borderColor)) {
			if (borderColor.trim().startsWith("#")) {
				java.awt.Color parse = java.awt.Color.decode(borderColor.trim().toUpperCase());
				this.theadBorderColor = new DeviceRgb(parse.getRed(), parse.getGreen(), parse.getBlue());
			} else {
				try {
					Field field = java.awt.Color.class.getField(borderColor.trim().toLowerCase());
					java.awt.Color parse = (java.awt.Color) field.get(null);
					this.theadBorderColor = new DeviceRgb(parse.getRed(), parse.getGreen(), parse.getBlue());
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

	public void setTheadBorderWidth(String borderWidth) {
		if (!Strings.isNullOrEmpty(borderWidth)) {
			try {
				this.theadBorderWidth = Float.valueOf(borderWidth.trim());
			} catch (NumberFormatException e) {
				throw new NumberFormatException("Elemento inválido para border-width, valor: " + borderWidth);
			}
		}
	}

	public void setTheadBorderTopWidth(String borderTopWidth) {
		if (!Strings.isNullOrEmpty(borderTopWidth)) {
			try {
				this.theadBorderTopWidth = Float.valueOf(borderTopWidth.trim());
			} catch (NumberFormatException e) {
				throw new NumberFormatException("Elemento inválido para border-top-width, valor: " + borderTopWidth);
			}
		}
	}

	public void setTheadBorderLeftWidth(String borderLeftWidth) {
		if (!Strings.isNullOrEmpty(borderLeftWidth)) {
			try {
				this.theadBorderLeftWidth = Float.valueOf(borderLeftWidth.trim());
			} catch (NumberFormatException e) {
				throw new NumberFormatException("Elemento inválido para border-left-width, valor: " + borderLeftWidth);
			}
		}
	}

	public void setTheadBorderRightWidth(String borderRightWidth) {
		if (!Strings.isNullOrEmpty(borderRightWidth)) {
			try {
				this.theadBorderRightWidth = Float.valueOf(borderRightWidth.trim());
			} catch (NumberFormatException e) {
				throw new NumberFormatException("Elemento inválido para border-right-width, valor: " + borderRightWidth);
			}
		}
	}

	public void setTheadBorderBottomWidth(String borderBottomWidth) {
		if (!Strings.isNullOrEmpty(borderBottomWidth)) {
			try {
				this.theadBorderBottomWidth = Float.valueOf(borderBottomWidth.trim());
			} catch (NumberFormatException e) {
				throw new NumberFormatException("Elemento inválido para border-bottom-width, valor: " + borderBottomWidth);
			}
		}
	}

	public void setTheadVerticalAlignment(String verticalAlignment) {
		if (!Strings.isNullOrEmpty(verticalAlignment)) {
			try {
				this.theadVerticalAlignment = VerticalAlignment.valueOf(verticalAlignment.trim().toUpperCase());
			} catch (NumberFormatException e) {
				throw new NumberFormatException("Elemento inválido para vertical-alignment, valor: " + verticalAlignment);
			}
		}
	}

	public void setTheadHorizontalAlignment(String horizontalAlignment) {
		if (!Strings.isNullOrEmpty(horizontalAlignment)) {
			try {
				this.theadHorizontalAlignment = HorizontalAlignment.valueOf(horizontalAlignment.trim().toUpperCase());
			} catch (NumberFormatException e) {
				throw new NumberFormatException("Elemento inválido para horizontal-alignment, valor: " + horizontalAlignment);
			}
		}
	}

	public void setTheadHeight(String height) {
		if (!Strings.isNullOrEmpty(height)) {
			try {
				this.theadHeight = Float.valueOf(height.trim());
			} catch (NumberFormatException e) {
				throw new NumberFormatException("Elemento inválido para height, valor: " + height);
			}
		}
	}

	public void setWidth(String width) {
		if (!Strings.isNullOrEmpty(width)) {
			try {
				this.width = Float.valueOf(width.trim());
			} catch (NumberFormatException e) {
				throw new NumberFormatException("Elemento inválido para width, valor: " + width);
			}
		}
	}

	public void setBackgroundCellColor(String backgroundCellColor) {
		if (!Strings.isNullOrEmpty(backgroundCellColor)) {
			if (backgroundCellColor.trim().startsWith("#")) {
				java.awt.Color parse = java.awt.Color.decode(backgroundCellColor.trim().toUpperCase());
				this.backgroundCellColor = new DeviceRgb(parse.getRed(), parse.getGreen(), parse.getBlue());
			} else {
				try {
					Field field = java.awt.Color.class.getField(backgroundCellColor.trim().toLowerCase());
					java.awt.Color parse = (java.awt.Color) field.get(null);
					this.backgroundCellColor = new DeviceRgb(parse.getRed(), parse.getGreen(), parse.getBlue());
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void setBackgroundCellColorPar(String backgroundCellColor) {
		if (!Strings.isNullOrEmpty(backgroundCellColor)) {
			if (backgroundCellColor.trim().startsWith("#")) {
				java.awt.Color parse = java.awt.Color.decode(backgroundCellColor.trim().toUpperCase());
				this.backgroundCellColorPar = new DeviceRgb(parse.getRed(), parse.getGreen(), parse.getBlue());
			} else {
				try {
					Field field = java.awt.Color.class.getField(backgroundCellColor.trim().toLowerCase());
					java.awt.Color parse = (java.awt.Color) field.get(null);
					this.backgroundCellColorPar = new DeviceRgb(parse.getRed(), parse.getGreen(), parse.getBlue());
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void setColspan(String colspan) {
		if (!Strings.isNullOrEmpty(colspan)) {
			this.colspan = Integer.valueOf(colspan.trim());
		}
	}

	@Override
	public void setRolspan(String rolspan) {
		if (!Strings.isNullOrEmpty(rolspan)) {
			this.rolspan = Integer.valueOf(rolspan.trim());
		}
	}

	public void setBorderColor(String borderColor) {
		if (!Strings.isNullOrEmpty(borderColor)) {
			if (borderColor.trim().startsWith("#")) {
				java.awt.Color parse = java.awt.Color.decode(borderColor.trim().toUpperCase());
				this.borderColor = new DeviceRgb(parse.getRed(), parse.getGreen(), parse.getBlue());
			} else {
				try {
					Field field = java.awt.Color.class.getField(borderColor.trim().toLowerCase());
					java.awt.Color parse = (java.awt.Color) field.get(null);
					this.borderColor = new DeviceRgb(parse.getRed(), parse.getGreen(), parse.getBlue());
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

	public void setBorderWidth(String borderWidth) {
		if (!Strings.isNullOrEmpty(borderWidth)) {
			try {
				this.borderWidth = Float.valueOf(borderWidth.trim());
			} catch (NumberFormatException e) {
				throw new NumberFormatException("Elemento inválido para border-width, valor: " + borderWidth);
			}
		}
	}

	public void setBorderTopWidth(String borderTopWidth) {
		if (!Strings.isNullOrEmpty(borderTopWidth)) {
			try {
				this.borderTopWidth = Float.valueOf(borderTopWidth.trim());
			} catch (NumberFormatException e) {
				throw new NumberFormatException("Elemento inválido para border-top-width, valor: " + borderTopWidth);
			}
		}
	}

	public void setBorderLeftWidth(String borderLeftWidth) {
		if (!Strings.isNullOrEmpty(borderLeftWidth)) {
			try {
				this.borderLeftWidth = Float.valueOf(borderLeftWidth.trim());
			} catch (NumberFormatException e) {
				throw new NumberFormatException("Elemento inválido para border-left-width, valor: " + borderLeftWidth);
			}
		}
	}

	public void setBorderRightWidth(String borderRightWidth) {
		if (!Strings.isNullOrEmpty(borderRightWidth)) {
			try {
				this.borderRightWidth = Float.valueOf(borderRightWidth.trim());
			} catch (NumberFormatException e) {
				throw new NumberFormatException("Elemento inválido para border-right-width, valor: " + borderRightWidth);
			}
		}
	}

	public void setBorderBottomWidth(String borderBottomWidth) {
		if (!Strings.isNullOrEmpty(borderBottomWidth)) {
			try {
				this.borderBottomWidth = Float.valueOf(borderBottomWidth.trim());
			} catch (NumberFormatException e) {
				throw new NumberFormatException("Elemento inválido para border-bottom-width, valor: " + borderBottomWidth);
			}
		}
	}

	public void setVerticalAlignment(String verticalAlignment) {
		if (!Strings.isNullOrEmpty(verticalAlignment)) {
			try {
				this.verticalAlignment = VerticalAlignment.valueOf(verticalAlignment.trim().toUpperCase());
			} catch (NumberFormatException e) {
				throw new NumberFormatException("Elemento inválido para vertical-alignment, valor: " + verticalAlignment);
			}
		}
	}

	public void setHorizontalAlignment(String horizontalAlignment) {
		if (!Strings.isNullOrEmpty(horizontalAlignment)) {
			try {
				this.horizontalAlignment = HorizontalAlignment.valueOf(horizontalAlignment.trim().toUpperCase());
			} catch (NumberFormatException e) {
				throw new NumberFormatException("Elemento inválido para horizontal-alignment, valor: " + horizontalAlignment);
			}
		}
	}

	public void setHeight(String height) {
		if (!Strings.isNullOrEmpty(height)) {
			try {
				this.height = Float.valueOf(height.trim());
			} catch (NumberFormatException e) {
				throw new NumberFormatException("Elemento inválido para height, valor: " + height);
			}
		}
	}

	public Color getBackgroundCellColorPar() {
		return backgroundCellColorPar;
	}

	public VerticalAlignment getTheadVerticalAlignment() {
		return theadVerticalAlignment;
	}

	public HorizontalAlignment getTheadHorizontalAlignment() {
		return theadHorizontalAlignment;
	}

	public Float getTheadHeight() {
		return theadHeight;
	}

	public Float getTheadWidth() {
		return theadWidth;
	}

	public Color getTheadBorderColor() {
		return theadBorderColor;
	}

	public Float getTheadBorderWidth() {
		return theadBorderWidth;
	}

	public Float getTheadBorderTopWidth() {
		return theadBorderTopWidth;
	}

	public Float getTheadBorderLeftWidth() {
		return theadBorderLeftWidth;
	}

	public Float getTheadBorderRightWidth() {
		return theadBorderRightWidth;
	}

	public Float getTheadBorderBottomWidth() {
		return theadBorderBottomWidth;
	}

	public int getTheadColspan() {
		return theadColspan;
	}

	public int getTheadRolspan() {
		return theadRolspan;
	}

	public Color getTheadBackgroundCellColor() {
		return theadBackgroundCellColor;
	}

	public VerticalAlignment getVerticalAlignment() {
		return verticalAlignment;
	}

	public HorizontalAlignment getHorizontalAlignment() {
		return horizontalAlignment;
	}

	public Float getHeight() {
		return height;
	}

	public Float getWidth() {
		return width;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public Float getBorderWidth() {
		return borderWidth;
	}

	public Float getBorderTopWidth() {
		return borderTopWidth;
	}

	public Float getBorderLeftWidth() {
		return borderLeftWidth;
	}

	public Float getBorderRightWidth() {
		return borderRightWidth;
	}

	public Float getBorderBottomWidth() {
		return borderBottomWidth;
	}

	public int getColspan() {
		return colspan;
	}

	public int getRolspan() {
		return rolspan;
	}

	public Color getBackgroundCellColor() {
		return backgroundCellColor;
	}

	public TextStyleClass getTheadNewTextStyle() {
		return theadTextStyle;
	}

	public TextStyleClass getNewTextStyle() {
		return textStyle;
	}

	@Override
	public void setTheadNewTextStyle(NewTextStyle style) {
		this.theadTextStyle = (TextStyleClass) style;
	}

	@Override
	public void setNewTextStyle(NewTextStyle style) {
		this.textStyle = (TextStyleClass) style;
	}
}
