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

public class TableDataStyleClass extends TextStyleClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2725789315961746383L;

	/** Cell table **/

	private VerticalAlignment verticalAlignment = VerticalAlignment.TOP;

	private HorizontalAlignment horizontalAlignment = HorizontalAlignment.LEFT;

	private Float height = null;
	private Float width = 1f;

	private Color borderColor = Color.BLACK;
	private Float borderWidth = 0.5f;
	private Float borderTopWidth = null;
	private Float borderLeftWidth = null;
	private Float borderRightWidth = null;
	private Float borderBottomWidth = null;
	private int colspan = 1;
	private int rolspan = 1;
	private Color backgroundCellColor = Color.WHITE;

	public static Optional<TextStyleClass> of(String style) {
		if (!Strings.isNullOrEmpty(style)) {
			return Optional.of(new TableDataStyleClass(style));
		}
		return Optional.of(new TableDataStyleClass(""));
	}

	protected TableDataStyleClass(String style) {
		super(style);
		try {
			if (style.contains(":")) {
				Map<String, String> map = Splitter.on(";").trimResults().omitEmptyStrings().withKeyValueSeparator(":").split(style);
				setVerticalAlignment(map.get("vertical-alignment"));
				setHorizontalAlignment(map.get("horizontal-alignment"));
				setHeight(map.get("height"));
				setBorderColor(map.get("border-color"));
				setBorderWidth(map.get("border-width"));

				setBorderTopWidth(map.get("border-top-width"));
				setBorderLeftWidth(map.get("border-left-width"));
				setBorderRightWidth(map.get("border-right-width"));
				setBorderBottomWidth(map.get("border-bottom-width"));

				setColspan(map.get("colspan"));
				setBackgroundCellColor(map.get("background-color"));
				setWidth(map.get("width"));
			} else if (!style.isEmpty()) {
				throw new Exception("formato css inválido, chave e valor separados por ':' e elementos separados por ';' ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setWidth(String width) {
		if (width != null) {
			try {
				this.width = Float.valueOf(width.trim());
			} catch (NumberFormatException e) {
				throw new NumberFormatException("Elemento inválido para width, valor: " + width);
			}
		}
	}

	private void setBackgroundCellColor(String backgroundCellColor) {
		if (backgroundCellColor != null) {
			if (backgroundCellColor.trim().startsWith("#")) {
				java.awt.Color parse = java.awt.Color.decode(backgroundCellColor.trim().toUpperCase());
				this.backgroundCellColor = new DeviceRgb(parse.getRed(), parse.getGreen(), parse.getBlue());
			} else {
				try {
					Field field = Color.class.getField(backgroundCellColor.trim().toUpperCase());
					this.backgroundCellColor = (Color) field.get(null);
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

	private void setColspan(String colspan) {
		if (colspan != null) {
			this.colspan = Integer.valueOf(colspan.trim());
		}
	}

	private void setBorderColor(String borderColor) {
		if (borderColor != null) {
			if (borderColor.trim().startsWith("#")) {
				java.awt.Color parse = java.awt.Color.decode(borderColor.trim().toUpperCase());
				this.borderColor = new DeviceRgb(parse.getRed(), parse.getGreen(), parse.getBlue());
			} else {
				try {
					Field field = Color.class.getField(borderColor.trim().toUpperCase());
					this.borderColor = (Color) field.get(null);
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

	private void setBorderWidth(String borderWidth) {
		if (borderWidth != null) {
			try {
				this.borderWidth = Float.valueOf(borderWidth.trim());
			} catch (NumberFormatException e) {
				throw new NumberFormatException("Elemento inválido para border-width, valor: " + borderWidth);
			}
		}
	}

	private void setBorderTopWidth(String borderTopWidth) {
		if (borderTopWidth != null) {
			try {
				this.borderTopWidth = Float.valueOf(borderTopWidth.trim());
			} catch (NumberFormatException e) {
				throw new NumberFormatException("Elemento inválido para border-top-width, valor: " + borderTopWidth);
			}
		}
	}

	private void setBorderLeftWidth(String borderLeftWidth) {
		if (borderLeftWidth != null) {
			try {
				this.borderLeftWidth = Float.valueOf(borderLeftWidth.trim());
			} catch (NumberFormatException e) {
				throw new NumberFormatException("Elemento inválido para border-left-width, valor: " + borderLeftWidth);
			}
		}
	}

	private void setBorderRightWidth(String borderRightWidth) {
		if (borderRightWidth != null) {
			try {
				this.borderRightWidth = Float.valueOf(borderRightWidth.trim());
			} catch (NumberFormatException e) {
				throw new NumberFormatException("Elemento inválido para border-right-width, valor: " + borderRightWidth);
			}
		}
	}

	private void setBorderBottomWidth(String borderBottomWidth) {
		if (borderBottomWidth != null) {
			try {
				this.borderBottomWidth = Float.valueOf(borderBottomWidth.trim());
			} catch (NumberFormatException e) {
				throw new NumberFormatException("Elemento inválido para border-bottom-width, valor: " + borderBottomWidth);
			}
		}
	}

	// public void setBorder(String border) {
	// if (border != null) {
	// try {
	// this.border = BorderStyle.valueOf(border.trim());
	// } catch (NumberFormatException e) {
	// throw new NumberFormatException("Elemento inválido para border, valor: "
	// + border);
	// }
	// }
	// }

	private void setVerticalAlignment(String verticalAlignment) {
		if (verticalAlignment != null) {
			try {
				this.verticalAlignment = VerticalAlignment.valueOf(verticalAlignment.trim().toUpperCase());
			} catch (NumberFormatException e) {
				throw new NumberFormatException("Elemento inválido para vertical-alignment, valor: " + verticalAlignment);
			}
		}
	}

	private void setHorizontalAlignment(String horizontalAlignment) {
		if (horizontalAlignment != null) {
			try {
				this.horizontalAlignment = HorizontalAlignment.valueOf(horizontalAlignment.trim().toUpperCase());
			} catch (NumberFormatException e) {
				throw new NumberFormatException("Elemento inválido para horizontal-alignment, valor: " + horizontalAlignment);
			}
		}
	}

	private void setHeight(String height) {
		if (height != null) {
			try {
				this.height = Float.valueOf(height.trim());
			} catch (NumberFormatException e) {
				throw new NumberFormatException("Elemento inválido para height, valor: " + height);
			}
		}
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



}
