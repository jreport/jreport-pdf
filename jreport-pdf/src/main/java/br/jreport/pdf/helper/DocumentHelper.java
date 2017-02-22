package br.jreport.pdf.helper;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.BiConsumer;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.AreaBreakType;

import br.jreport.core.api.NewTableRow;
import br.jreport.core.api.property.NewTableProperty;
import br.jreport.pdf.aux.PdfTableRow;
import br.jreport.pdf.enums.TextDecoration;
import br.jreport.pdf.style.ImageStyleClass;
import br.jreport.pdf.style.TableDataStyleClass;
import br.jreport.pdf.style.TextStyleClass;

public class DocumentHelper {
	//@formatter:off
	public static Paragraph newLine() {
		return new Paragraph("");
	}

	public static AreaBreak newPage() {
		return new AreaBreak(AreaBreakType.NEXT_PAGE);
	}

	private static Paragraph setupText(String text, String styleClass) {
		text = text == null || text.isEmpty() ? " " : text;
		TextStyleClass textStyle = TextStyleClass.of(styleClass).get();
		Paragraph p = null;
		try {
			PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA);
			Text pdftext  = new Text(text);
			pdftext.setFont(font);
			pdftext.setFontSize(textStyle.getFontSize());
			if (textStyle.isFontBold()) {
				pdftext.setBold();
			}
			if (textStyle.isFontStyle()) {
				pdftext.setItalic();
			}
			pdftext.setFontColor(textStyle.getColor());
			pdftext.setTextAlignment(textStyle.getTextAlign());
			if (textStyle.getTextDecoration().equals(TextDecoration.UNDERLINE)) {
				pdftext.setUnderline();
			}else if (textStyle.getTextDecoration().equals(TextDecoration.LINE_THROUGH)) {
				pdftext.setLineThrough();
			}
			p = new Paragraph(pdftext);
			p.setFirstLineIndent(textStyle.getTextIndent());
			p.setMarginLeft(textStyle.getTextMarginLeft());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p;
	}
	
	public static Cell setupCell(Paragraph p, String estilo) {
		TableDataStyleClass styleClass = (TableDataStyleClass) TableDataStyleClass.of(estilo).get();
		Cell cell = new Cell(styleClass.getRolspan(), styleClass.getColspan());
		cell.add(p);
		if (styleClass.getHeight() != null) {
			cell.setHeight(styleClass.getHeight());
		}
		cell.setHorizontalAlignment(styleClass.getHorizontalAlignment());
		cell.setVerticalAlignment(styleClass.getVerticalAlignment());
		cell.setBorder(new SolidBorder(styleClass.getBorderColor(), styleClass.getBorderWidth()));

		if (styleClass.getBorderBottomWidth() != null) {
			cell.setBorderBottom(new SolidBorder(styleClass.getBorderColor(), styleClass.getBorderBottomWidth()));
		}
		if (styleClass.getBorderLeftWidth() != null) {
			cell.setBorderLeft(new SolidBorder(styleClass.getBorderColor(), styleClass.getBorderLeftWidth()));
		}
		if (styleClass.getBorderRightWidth() != null) {
			cell.setBorderRight(new SolidBorder(styleClass.getBorderColor(), styleClass.getBorderRightWidth()));
		}
		if (styleClass.getBorderTopWidth() != null) {
			cell.setBorderTop(new SolidBorder(styleClass.getBorderColor(), styleClass.getBorderTopWidth()));
		}

		cell.setBackgroundColor(styleClass.getBackgroundCellColor());
		return cell;
	}
	
	public static <T> Table createTable(NewTableProperty<T> tableAdapter, String classe, String headerClasse) {
		if (tableAdapter.numColumns() > 0) {
			Table pdfPTable = new Table(tableAdapter.numColumns());
			tableAdapter.getHeaders().stream().forEach(th -> pdfPTable.addCell(setupCell(setupText(th.getText(), headerClasse), headerClasse)));
			int numColumns = tableAdapter.numColumns();
			int numRows = tableAdapter.getDatasource().getList().size();
			for (int row = 0; row < numRows; row++) {
				T item = tableAdapter.getDatasource().getList().get(row);
				for (int column = 0; column < numColumns; column++) {
					tableAdapter.getColumn(item, column).ifPresent(td -> pdfPTable.addCell(setupCell(setupText(td.getText(), classe), classe)));
				}
			}
			return pdfPTable;
		}
		return null;
	}
	
	public static <T> Table createTable(NewTableProperty<T> tableAdapter, BiConsumer<T, NewTableRow> eachRow, String classe, String headerClasse) {
		if (tableAdapter.numColumns() > 0) {
			Table pdfPTable = new Table(tableAdapter.numColumns());
			tableAdapter.getHeaders().stream().forEach(th -> pdfPTable.addCell(setupCell(setupText(th.getText(), headerClasse), headerClasse)));
			int qtdColumns = tableAdapter.numColumns();
			int qtdRows = tableAdapter.getDatasource().getList().size();
			// Iteração por linha
			for (int row = 0; row < qtdRows; row++) {
				T item = tableAdapter.getDatasource().getList().get(row);
				boolean last = row == qtdRows - 1;
				boolean first = row == 0;
				boolean odd = row % 2 == 1;
				boolean even = row % 2 == 0;
				NewTableRow tableRow = new PdfTableRow(row, last, first, odd, even);
				eachRow.accept(item, tableRow);
				// Iteração por colunas
				for (int column = 0; column < qtdColumns; column++) {
					tableAdapter.getColumn(item, column).ifPresent(td -> pdfPTable.addCell(setupCell(setupText(td.getText(), classe), classe)));
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

//	public static PdfPCell addElementCellToTable(Element p) {
//		PdfPCell cell = new PdfPCell();
//		cell.addElement(p);
//
//		cell.setBorderWidthBottom(0);
//		cell.setBorderWidthLeft(0);
//		cell.setBorderWidthRight(0);
//		cell.setBorderWidthTop(0);
//
//		return cell;
//	}

	public static Paragraph createText(String text, String styleClass) {
		return setupText(text, styleClass);
	}

	/**
	 * 
	 * @param number
	 * @return
	 */
	public static Paragraph createText(BigDecimal number) {
		String text = String.valueOf(number);
		return setupText(text, "");
	}

	public static Paragraph createText(BigDecimal number, String styleClass) {
		String text = String.valueOf(number);
		return setupText(text, styleClass);
	}

	/**
	 * 
	 * @param number
	 * @return
	 */
	public static Paragraph createText(Long number) {
		String text = String.valueOf(number);
		return setupText(text, "");
	}

	public static Paragraph createText(Long number, String styleClass) {
		String text = String.valueOf(number);
		return setupText(text, styleClass);
	}

	/**
	 * 
	 * @param number
	 * @return
	 */
	public static Paragraph createText(Integer number) {
		String text = String.valueOf(number);
		return setupText(text, "");
	}

	public static Paragraph createText(Integer number, String styleClass) {
		String text = String.valueOf(number);
		return setupText(text, styleClass);
	}

	/**
	 * 
	 * @param bool
	 * @return
	 */
	public static Paragraph createText(Boolean bool) {
		String text = String.valueOf(bool);
		return setupText(text, "");
	}

	public static Paragraph createText(Boolean bool, String styleClass) {
		String text = String.valueOf(bool);
		return setupText(text, styleClass);
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Paragraph createText(Date date) {
		String text = new SimpleDateFormat("dd/MM/yyyy").format(date);
		return setupText(text, "");
	}

	public static Paragraph createText(Date date, String pattern) {
		String text = new SimpleDateFormat(pattern).format(date);
		return setupText(text, "");
	}

	public static Paragraph createText(Date date, String pattern, String styleClass) {
		String text = new SimpleDateFormat(pattern).format(date);
		return setupText(text, styleClass);
	}

	public static Image loadImage(String imageName, String styleClass) {
		ImageStyleClass imageStyle = ImageStyleClass.of(styleClass).get();
		URL imageURL = DocumentHelper.class.getClassLoader().getResource(imageName);
		Image image = new Image(ImageDataFactory.create(imageURL));

		image.setHorizontalAlignment(imageStyle.getAlign());
		if (imageStyle.getHeight() != null) {
			image.setHeight(imageStyle.getHeight());
		}
		if (imageStyle.getWidth() != null) {
			image.setWidth(imageStyle.getWidth());
		}
		return image;
	}

	public static LineSeparator createDefaultSeparator() {
		SolidLine line = new SolidLine(0.1f);
		LineSeparator separator = new LineSeparator(line);
		separator.setMarginTop(2);
		return separator;
	}
	//@formatter:oN
}
