package br.jreport.pdf.helper;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.PdfCanvasConstants;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.AreaBreakType;
import com.itextpdf.layout.renderer.CellRenderer;
import com.itextpdf.layout.renderer.DrawContext;

import br.jreport.core.api.NewTableHeader;
import br.jreport.core.api.aux.NewTableRow;
import br.jreport.core.api.property.NewTableProperty;
import br.jreport.core.enumerations.HeaderType;
import br.jreport.core.impl.Style;
import br.jreport.pdf.aux.PdfTableRow;
import br.jreport.pdf.enums.TextDecoration;
import br.jreport.pdf.style.ImageStyleClass;
import br.jreport.pdf.style.TableDataStyleClass;
import br.jreport.pdf.style.TextStyleClass;

public class DocumentHelper {

	final static ILineDash solid = new Solid();
	final static ILineDash dotted = new Dotted();
	final static ILineDash dashed = new Dashed();

	//@formatter:off
	public static Paragraph newLine() {
		return new Paragraph("");
	}

	public static AreaBreak newPage() {
		return new AreaBreak(AreaBreakType.NEXT_PAGE);
	}

	private static Paragraph setupText(String text, Style styleClass) {
		text = text == null || text.isEmpty() ? " " : text;
		TextStyleClass textStyle = TextStyleClass.of(styleClass).get();
		Paragraph p = null;
//		try {
//			PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA);
			Text pdftext  = new Text(text);
//			pdftext.setFont(font);
			pdftext.setFontSize(textStyle.getFontSize());
			if (textStyle.isFontBold()) {
				pdftext.setBold();
			}
			if (textStyle.isFontStyle()) {
				pdftext.setItalic();
			}
			pdftext.setFontColor(textStyle.getColor());
			pdftext.setTextAlignment(textStyle.getTextAlign());
			if (textStyle.getTextDecoration() != null && textStyle.getTextDecoration().equals(TextDecoration.UNDERLINE)) {
				pdftext.setUnderline();
			}else if (textStyle.getTextDecoration() != null &&textStyle.getTextDecoration().equals(TextDecoration.LINE_THROUGH)) {
				pdftext.setLineThrough();
			}
			p = new Paragraph();
			p.setTextAlignment(textStyle.getTextAlign());
			p.setFirstLineIndent(textStyle.getTextIndent());
			p.setMarginLeft(textStyle.getTextMarginLeft());
			p.setMargin(0);
			p.add(pdftext);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		return p;
	}
	
	
	public static Cell setupTHeaderCell(NewTableHeader p, Style estilo) {
		TableDataStyleClass styleClass = (TableDataStyleClass) TableDataStyleClass.of(estilo).get();
//		styleClass.setClasseEstilo(p.getEstilo());
		Cell cell = new Cell(styleClass.getTheadRolspan(), styleClass.getTheadColspan());
		cell.add(setupCellText(p.getText(), styleClass.getTheadNewTextStyle(), p.getType()));
		if (styleClass.getTheadHeight() != null) {
			cell.setHeight(styleClass.getTheadHeight());
		}
		cell.setHorizontalAlignment(styleClass.getTheadHorizontalAlignment());
		cell.setVerticalAlignment(styleClass.getTheadVerticalAlignment());
		
		cell.setBorder(Border.NO_BORDER);
	    cell.setNextRenderer(new CustomTheadBorder3Renderer(cell,
	            new ILineDash[]{solid, solid, solid, solid}, styleClass));

		cell.setBackgroundColor(styleClass.getTheadBackgroundCellColor());
		return cell;
	}
	
	public static Cell setupCell(String p, Style estilo, Integer row) {
		TableDataStyleClass styleClass = (TableDataStyleClass) TableDataStyleClass.of(estilo).get();
		Cell cell = new Cell(styleClass.getRolspan(), styleClass.getColspan());
		cell.add(setupCellText(p, styleClass.getNewTextStyle(), null));
		if (styleClass.getHeight() != null) {
			cell.setHeight(styleClass.getHeight());
		}
		cell.setHorizontalAlignment(styleClass.getHorizontalAlignment());
		cell.setVerticalAlignment(styleClass.getVerticalAlignment());
		
		cell.setBorder(Border.NO_BORDER);
	    cell.setNextRenderer(new CustomBorder3Renderer(cell,
	            new ILineDash[]{solid, solid, solid, solid}, styleClass));
	    //Linhas zebradas
		if (styleClass.getBackgroundCellColorPar()!=null) {
			if (Math.abs(row % 2) == 0) {
				cell.setBackgroundColor(styleClass.getBackgroundCellColorPar());
			}else {
				cell.setBackgroundColor(styleClass.getBackgroundCellColor());
			}
		}else {
			cell.setBackgroundColor(styleClass.getBackgroundCellColor());
		}
		return cell;
	}
	
	private static Paragraph setupCellText(String text, TextStyleClass styleClass, HeaderType headerType) {
		text = text == null || text.isEmpty() ? " " : text;
		Paragraph p = null;
		try {
			PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA);
			Text pdftext  = new Text(text);
			pdftext.setFont(font);
			pdftext.setFontSize(styleClass.getFontSize());
			if (styleClass.isFontBold()) {
				pdftext.setBold();
			}
			if (styleClass.isFontStyle()) {
				pdftext.setItalic();
			}
			pdftext.setFontColor(styleClass.getColor());
//			pdftext.setTextAlignment(styleClass.getTextAlign());
			if (styleClass.getTextDecoration() != null && styleClass.getTextDecoration().equals(TextDecoration.UNDERLINE)) {
				pdftext.setUnderline();
			}else if (styleClass.getTextDecoration() != null &&styleClass.getTextDecoration().equals(TextDecoration.LINE_THROUGH)) {
				pdftext.setLineThrough();
			}
			p = paragraph(text, styleClass, headerType, pdftext);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p;
	}

	private static Paragraph paragraph(String text, TextStyleClass styleClass, HeaderType headerType, Text pdftext) {
		Paragraph p;
		p = new Paragraph(pdftext);
		if (headerType != null) {
			if(headerType == HeaderType.NUMBER){
				p.setTextAlignment(styleClass.getTextAlignNumber());
			}else if(headerType == HeaderType.TEXT){
				p.setTextAlignment(styleClass.getTextAlign());
			}
			p.setFirstLineIndent(styleClass.getTextIndent());
			p.setMarginLeft(styleClass.getTextMarginLeft());
		}else {
			if(text.matches("^[0-9]*[.,]{0,1}[0-9]*$")){
				p.setTextAlignment(styleClass.getTextAlignNumber());
			}else {
				p.setTextAlignment(styleClass.getTextAlign());
			}
			p.setFirstLineIndent(styleClass.getTextIndent());
			p.setMarginLeft(styleClass.getTextMarginLeft());
		}
		return p;
	}
	
	public static Paragraph createText(String text, Optional<Style> styleClass) {
		if (styleClass.isPresent()) {
			return setupText(text, styleClass.get());
		}
		return null;
	}

	/**
	 * 
	 * @param number
	 * @return
	 */
	public static Paragraph createText(BigDecimal number) {
		String text = String.valueOf(number);
		return setupText(text, Style.of("").get());
	}

	public static Paragraph createText(BigDecimal number, Optional<Style> styleClass) {
		if (styleClass.isPresent()) {
			String text = String.valueOf(number);
			return setupText(text, styleClass.get());
		}
		return null;
	}

	/**
	 * 
	 * @param number
	 * @return
	 */
	public static Paragraph createText(Long number) {
		String text = String.valueOf(number);
		return setupText(text, Style.of("").get());
	}

	public static Paragraph createText(Long number, Optional<Style> styleClass) {
		if (styleClass.isPresent()) {
			String text = String.valueOf(number);
			return setupText(text, styleClass.get());
		}
		return null;
	}

	/**
	 * 
	 * @param number
	 * @return
	 */
	public static Paragraph createText(Integer number) {
		String text = String.valueOf(number);
		return setupText(text, Style.of("").get());
	}

	public static Paragraph createText(Integer number, Optional<Style> styleClass) {
		if (styleClass.isPresent()) {
			String text = String.valueOf(number);
			return setupText(text, styleClass.get());
		}
		return null;
		
	}

	/**
	 * 
	 * @param bool
	 * @return
	 */
	public static Paragraph createText(Boolean bool) {
		String text = String.valueOf(bool);
		return setupText(text, Style.of("").get());
	}

	public static Paragraph createText(Boolean bool, Optional<Style> styleClass) {
		if (styleClass.isPresent()) {
			String text = String.valueOf(bool);
			return setupText(text, styleClass.get());
		}
		return null;
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Paragraph createText(Date date) {
		String text = new SimpleDateFormat("dd/MM/yyyy").format(date);
		return setupText(text, Style.of("").get());
	}

	public static Paragraph createText(Date date, String pattern) {
		String text = new SimpleDateFormat(pattern).format(date);
		return setupText(text, Style.of("").get());
	}

	public static Paragraph createText(Date date, String pattern, Optional<Style> styleClass) {
		if (styleClass.isPresent()) {
			String text = new SimpleDateFormat(pattern).format(date);
			return setupText(text, styleClass.get());
		}
		return null;
	}

	public static Image loadImage(String imageName, Optional<Style> styleClass) {
		if (styleClass.isPresent()) {
			ImageStyleClass imageStyle = ImageStyleClass.of(styleClass.get()).get();
			URL imageURL = DocumentHelper.class.getClassLoader().getResource(imageName);
			Image image = new Image(ImageDataFactory.create(imageURL));

			image.setHorizontalAlignment(imageStyle.getAlign());
			if (imageStyle.getHeight() != null) {
				image.setHeight(imageStyle.getHeight());
			}
			if (imageStyle.getWidth() != null) {
				image.setWidth(imageStyle.getWidth());
			}
			image.setMargins(imageStyle.getMarginTop(), imageStyle.getMarginRight(), imageStyle.getMarginBottom(), imageStyle.getMarginLeft());
			return image;
		}
		return null;
	}

	public static LineSeparator createDefaultSeparator() {
		SolidLine line = new SolidLine(0.1f);
		LineSeparator separator = new LineSeparator(line);
		separator.setMarginTop(2);
		return separator;
	}
	
	

	/**
	 * 
	 * Table
	 * 
	 */
	
	public static <T>  Cell tableSpacer(Integer numColumns, Table pdfPTable) {
		Cell cell = new Cell();
		cell.setBorder(Border.NO_BORDER);
		for (int column = 0; column < numColumns; column++) {
			pdfPTable.addCell(cell);
		}
		return cell;
	}
	
	public static <T> Table createTable(NewTableProperty<T> tableAdapter, Optional<Style> styleClass) {
		if (styleClass.isPresent() && tableAdapter.numColumns() > 0) {
			Table pdfPTable = new Table(tableAdapter.numColumns());
			tableAdapter.getHeaders().stream().forEach(th -> pdfPTable.addCell(setupTHeaderCell(th, styleClass.get())));
			int numColumns = tableAdapter.numColumns();
			int numElements = tableAdapter.getDatasource().getList().size();
			tableSpacer(numColumns, pdfPTable);
			for (int element = 0; element < numElements; element++) {
				final int val = element;
				T item = tableAdapter.getDatasource().getList().get(element);
				for (int column = 0; column < numColumns; column++) {
					tableAdapter.getColumn(item, column).ifPresent(tc -> pdfPTable.addCell(setupCell(tc.getText(), styleClass.get(), val)));
				}
			}
			return pdfPTable;
		}
		return null;
	}
	
	public static <T> Table createTable(NewTableProperty<T> tableAdapter, BiConsumer<T, NewTableRow> eachRow, Optional<Style> styleClass) {
		if (styleClass.isPresent()&& tableAdapter.numColumns() > 0) {
			Table pdfPTable = new Table(tableAdapter.numColumns());
			tableAdapter.getHeaders().stream().forEach(th -> pdfPTable.addCell(setupTHeaderCell(th, styleClass.get())));
			int qtdColumns = tableAdapter.numColumns();
			int qtdRows = tableAdapter.getDatasource().getList().size();
			tableSpacer(qtdColumns, pdfPTable);
			// Iteração por linha
			for (int row = 0; row < qtdRows; row++) {
				final int val = row;
				T item = tableAdapter.getDatasource().getList().get(row);
				boolean last = row == qtdRows - 1;
				boolean first = row == 0;
				boolean odd = row % 2 == 1;
				boolean even = row % 2 == 0;
				NewTableRow tableRow = new PdfTableRow(row, last, first, odd, even);
				eachRow.accept(item, tableRow);
				// Iteração por colunas
				for (int column = 0; column < qtdColumns; column++) {
					tableAdapter.getColumn(item, column).ifPresent(td -> pdfPTable.addCell(setupCell(td.getText(), styleClass.get(), val)));
				}
			}
			return pdfPTable;
		}
		return null;
	}	
	
	
	public static Table createColspan(Integer cols, List<Cell> cells) {
		Table colspan = new Table(cols);
		cells.forEach(cell -> colspan.addCell(cell));
		return colspan;
	}
	
}

/** 
 * Params new ILineDash[]{{TOP}, {LEFT}, {BOTTOM}, {RIGHT}})
 * */
interface ILineDash {
    void applyLineDash(PdfCanvas canvas);
}

class Solid implements ILineDash {
    public void applyLineDash(PdfCanvas canvas) {
    }
}

class Dotted implements ILineDash {
    public void applyLineDash(PdfCanvas canvas) {
        canvas.setLineCapStyle(PdfCanvasConstants.LineCapStyle.ROUND);
        canvas.setLineDash(0, 4, 2);
    }
}
class Dashed implements ILineDash {
    public void applyLineDash(PdfCanvas canvas) {
        canvas.setLineDash(3, 3);
    }
}


class CustomBorder3Renderer extends CellRenderer {
    ILineDash[] borders;
    TableDataStyleClass styleClass;
    public CustomBorder3Renderer(Cell modelElement, ILineDash[] borders, TableDataStyleClass style) {
        super(modelElement);
        this.borders = new ILineDash[borders.length];
        this.styleClass = style;
        for (int i = 0; i < this.borders.length; i++) {
            this.borders[i] = borders[i];
        }
    }
    
    @Override
    public void draw(DrawContext drawContext) {
        super.draw(drawContext);
        PdfCanvas canvas = drawContext.getCanvas();
        Rectangle position = getOccupiedAreaBBox();
        canvas.saveState();
        if (null != borders[0] && styleClass.getBorderTopWidth() != null && styleClass.getBorderTopWidth() != 0) {
        	 canvas.saveState();
             borders[0].applyLineDash(canvas);
             canvas.moveTo(position.getRight(), position.getTop());
             canvas.lineTo(position.getLeft(), position.getTop());
            if (styleClass.getBorderColor() != null) {
            	canvas.setStrokeColor(styleClass.getBorderColor());
            }
            if (styleClass.getBorderTopWidth() != null) {
            	canvas.setLineWidth(styleClass.getBorderTopWidth());
    		}
            canvas.stroke();
            canvas.restoreState();
        }
        if (null != borders[2] && styleClass.getBorderBottomWidth() != null && styleClass.getBorderBottomWidth() != 0) {
            canvas.saveState();
            borders[2].applyLineDash(canvas);
            canvas.moveTo(position.getRight(), position.getBottom());
            canvas.lineTo(position.getLeft(), position.getBottom());
            if (styleClass.getBorderColor() != null) {
            	canvas.setStrokeColor(styleClass.getBorderColor());
            }
            if (styleClass.getBorderBottomWidth() != null) {
            	canvas.setLineWidth(styleClass.getBorderBottomWidth());
    		}
            canvas.stroke();
            canvas.restoreState();
        }
        if (null != borders[3]&& styleClass.getBorderRightWidth() != null && styleClass.getBorderRightWidth() != 0) {
            canvas.saveState();
            borders[3].applyLineDash(canvas);
            canvas.moveTo(position.getRight(), position.getTop());
            canvas.lineTo(position.getRight(), position.getBottom());
            if (styleClass.getBorderColor() != null) {
            	canvas.setStrokeColor(styleClass.getBorderColor());
            }
            if (styleClass.getBorderRightWidth() != null) {
            	canvas.setLineWidth(styleClass.getBorderRightWidth());
    		}
            canvas.stroke();
            canvas.restoreState();
        }
        if (null != borders[1] && styleClass.getBorderLeftWidth() != null && styleClass.getBorderLeftWidth() != 0) {
            canvas.saveState();
            borders[1].applyLineDash(canvas);
            canvas.moveTo(position.getLeft(), position.getTop());
            canvas.lineTo(position.getLeft(), position.getBottom());
            if (styleClass.getBorderColor() != null) {
            	canvas.setStrokeColor(styleClass.getBorderColor());
            }
            if (styleClass.getBorderLeftWidth() != null) {
            	canvas.setLineWidth(styleClass.getBorderLeftWidth());
    		}
            canvas.stroke();
            canvas.restoreState();
        }
        canvas.stroke();
        canvas.restoreState();
    }
}
class CustomTheadBorder3Renderer extends CellRenderer {
	ILineDash[] borders;
	TableDataStyleClass styleClass;
	public CustomTheadBorder3Renderer(Cell modelElement, ILineDash[] borders, TableDataStyleClass style) {
		super(modelElement);
		this.borders = new ILineDash[borders.length];
		this.styleClass = style;
		for (int i = 0; i < this.borders.length; i++) {
			this.borders[i] = borders[i];
		}
	}
	
	@Override
	public void draw(DrawContext drawContext) {
		super.draw(drawContext);
		PdfCanvas canvas = drawContext.getCanvas();
		Rectangle position = getOccupiedAreaBBox();
		canvas.saveState();
		if (null != borders[0] && styleClass.getTheadBorderTopWidth() != null && styleClass.getTheadBorderTopWidth() != 0) {
			canvas.saveState();
			borders[0].applyLineDash(canvas);
			canvas.moveTo(position.getRight(), position.getTop());
			canvas.lineTo(position.getLeft(), position.getTop());
			if (styleClass.getTheadBorderColor() != null) {
				canvas.setStrokeColor(styleClass.getTheadBorderColor());
			}
			if (styleClass.getTheadBorderTopWidth() != null) {
				canvas.setLineWidth(styleClass.getTheadBorderTopWidth());
			}
			canvas.stroke();
			canvas.restoreState();
		}
		if (null != borders[2] && styleClass.getTheadBorderBottomWidth() != null && styleClass.getTheadBorderBottomWidth() != 0) {
			canvas.saveState();
			borders[2].applyLineDash(canvas);
			canvas.moveTo(position.getRight(), position.getBottom());
			canvas.lineTo(position.getLeft(), position.getBottom());
			if (styleClass.getTheadBorderColor() != null) {
				canvas.setStrokeColor(styleClass.getTheadBorderColor());
			}
			if (styleClass.getTheadBorderBottomWidth() != null) {
				canvas.setLineWidth(styleClass.getTheadBorderBottomWidth());
			}
			canvas.stroke();
			canvas.restoreState();
		}
		if (null != borders[3]&& styleClass.getTheadBorderRightWidth() != null && styleClass.getTheadBorderRightWidth() != 0) {
			canvas.saveState();
			borders[3].applyLineDash(canvas);
			canvas.moveTo(position.getRight(), position.getTop());
			canvas.lineTo(position.getRight(), position.getBottom());
			if (styleClass.getTheadBorderColor() != null) {
				canvas.setStrokeColor(styleClass.getTheadBorderColor());
			}
			if (styleClass.getTheadBorderRightWidth() != null) {
				canvas.setLineWidth(styleClass.getTheadBorderRightWidth());
			}
			canvas.stroke();
			canvas.restoreState();
		}
		if (null != borders[1] && styleClass.getTheadBorderLeftWidth() != null && styleClass.getTheadBorderLeftWidth() != 0) {
			canvas.saveState();
			borders[1].applyLineDash(canvas);
			canvas.moveTo(position.getLeft(), position.getTop());
			canvas.lineTo(position.getLeft(), position.getBottom());
			if (styleClass.getTheadBorderColor() != null) {
				canvas.setStrokeColor(styleClass.getTheadBorderColor());
			}
			if (styleClass.getTheadBorderLeftWidth() != null) {
				canvas.setLineWidth(styleClass.getTheadBorderLeftWidth());
			}
			canvas.stroke();
			canvas.restoreState();
		}
		canvas.stroke();
		canvas.restoreState();
	}
	
	
}
