package br.jreport.pdf.helper;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.draw.LineSeparator;

import br.jreport.pdf.enums.TextDecoration;
import br.jreport.pdf.style.TextStyleClass;

public class DocumentHelper {
	//@formatter:off
	public static Chunk newLine() {
		return Chunk.NEWLINE;
	}

	public static void newPage(Document document) {
		document.newPage();
	}

	private static Paragraph setupText(String text, TextStyleClass styleClass) {
		text = text == null || text.isEmpty() ? " " : text;
		Paragraph p = null;
		if (styleClass!=null) {
			Font f = new Font(Font.HELVETICA);
			f.setSize(styleClass.getFontSize());
			f.setStyle(styleClass.getFontStyle().getValue());
			f.setColor(styleClass.getColor());

			Chunk chunk = new Chunk(text, f);
			if (styleClass.getTextDecoration() != TextDecoration.NONE) {
				chunk.setUnderline(styleClass.getTextDecoration().getThick(), styleClass.getTextDecoration().getY());
			}
			p = new Paragraph(chunk);
			p.setFirstLineIndent(styleClass.getTextIndent());
			p.setIndentationLeft(styleClass.getMarginLeft());
			p.setAlignment(styleClass.getTextAlign().getValue());	
		}else {
			p = new Paragraph();
			p.add(text);
		}

		return p;
	}

//	public static void createPdfPCell(Paragraph p, TableDataStyleClass styleClass, PdfPTable pdfPTable) {
//		PdfPCell cell = new PdfPCell();
//		cell.addElement(p);
//		if (styleClass.getHeight() != null) {
//			cell.setFixedHeight(styleClass.getHeight());
//		}
//		cell.setHorizontalAlignment(styleClass.getHorizontalAlignment());
//		cell.setVerticalAlignment(styleClass.getVerticalAlignment());
//		cell.setBorderColor(styleClass.getBorderColor());
//		cell.setColspan(styleClass.getColspan());
//		cell.setBackgroundColor(styleClass.getBackgroundCellColor());
//
//		cell.setBorderWidth(styleClass.getBorderWidth());
//
//		if (styleClass.getBorderBottomWidth() != null) {
//			cell.setBorderWidthBottom(styleClass.getBorderBottomWidth());
//		}
//		if (styleClass.getBorderLeftWidth() != null) {
//			cell.setBorderWidthLeft(styleClass.getBorderLeftWidth());
//		}
//		if (styleClass.getBorderRightWidth() != null) {
//			cell.setBorderWidthRight(styleClass.getBorderRightWidth());
//		}
//		if (styleClass.getBorderTopWidth() != null) {
//			cell.setBorderWidthTop(styleClass.getBorderTopWidth());
//		}
//
//		pdfPTable.addCell(cell);
//
//	}

//	public static PdfPCell createPdfPCell(Paragraph p, TableDataStyleClass styleClass) {
//		PdfPCell cell = new PdfPCell();
//		cell.addElement(p);
//		if (styleClass.getHeight() != null) {
//			cell.setFixedHeight(styleClass.getHeight());
//		}
//		cell.setHorizontalAlignment(styleClass.getHorizontalAlignment());
//		cell.setVerticalAlignment(styleClass.getVerticalAlignment());
//		cell.setBorderWidth(styleClass.getBorderWidth());
//
//		if (styleClass.getBorderBottomWidth() != null) {
//			cell.setBorderWidthBottom(styleClass.getBorderBottomWidth());
//		}
//		if (styleClass.getBorderLeftWidth() != null) {
//			cell.setBorderWidthLeft(styleClass.getBorderLeftWidth());
//		}
//		if (styleClass.getBorderRightWidth() != null) {
//			cell.setBorderWidthRight(styleClass.getBorderRightWidth());
//		}
//		if (styleClass.getBorderTopWidth() != null) {
//			cell.setBorderWidthTop(styleClass.getBorderTopWidth());
//		}
//
//		cell.setBorderColor(styleClass.getBorderColor());
//		cell.setColspan(styleClass.getColspan());
//		cell.setBackgroundColor(styleClass.getBackgroundCellColor());
//		return cell;
//	}

	public static PdfPCell addElementCellToTable(Element p) {
		PdfPCell cell = new PdfPCell();
		cell.addElement(p);

		cell.setBorderWidthBottom(0);
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthTop(0);

		return cell;
	}

	/**
	 * 
	 * @param text
	 * @return
	 */
	public static Paragraph createText(String text) {
		return setupText(text, TextStyleClass.of("").get());
	}

	public static Paragraph createText(String text, String styleClass) {
		return setupText(text, TextStyleClass.of(styleClass).get());
	}

	/**
	 * 
	 * @param number
	 * @return
	 */
	public static Paragraph createText(BigDecimal number) {
		String text = String.valueOf(number);
		return setupText(text, TextStyleClass.of("").get());
	}

	public static Paragraph createText(BigDecimal number, String styleClass) {
		String text = String.valueOf(number);
		return setupText(text, TextStyleClass.of(styleClass).get());
	}

	/**
	 * 
	 * @param number
	 * @return
	 */
	public static Paragraph createText(Long number) {
		String text = String.valueOf(number);
		return setupText(text, TextStyleClass.of("").get());
	}

	public static Paragraph createText(Long number, String styleClass) {
		String text = String.valueOf(number);
		return setupText(text, TextStyleClass.of(styleClass).get());
	}

	/**
	 * 
	 * @param number
	 * @return
	 */
	public static Paragraph createText(Integer number) {
		String text = String.valueOf(number);
		return setupText(text, TextStyleClass.of("").get());
	}

	public static Paragraph createText(Integer number, String styleClass) {
		String text = String.valueOf(number);
		return setupText(text, TextStyleClass.of(styleClass).get());
	}

	/**
	 * 
	 * @param bool
	 * @return
	 */
	public static Paragraph createText(Boolean bool) {
		String text = String.valueOf(bool);
		return setupText(text, TextStyleClass.of("").get());
	}

	public static Paragraph createText(Boolean bool, String styleClass) {
		String text = String.valueOf(bool);
		return setupText(text, TextStyleClass.of(styleClass).get());
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Paragraph createText(Date date) {
		String text = new SimpleDateFormat("dd/MM/yyyy").format(date);
		return setupText(text, TextStyleClass.of("").get());
	}

	public static Paragraph createText(Date date, String pattern) {
		String text = new SimpleDateFormat(pattern).format(date);
		return setupText(text, TextStyleClass.of("").get());
	}

	public static Paragraph createText(Date date, String pattern, String styleClass) {
		String text = new SimpleDateFormat(pattern).format(date);
		return setupText(text, TextStyleClass.of(styleClass).get());
	}

	public static Image loadImage(String imageName) {
		try {
			URL imageURL = DocumentHelper.class.getClassLoader().getResource(imageName);
			Image image = Image.getInstance(imageURL);
//			ImageStyleClass style = new ImageStyleClass();
//			image.setAlignment(style.getAlign().getValue());
			return image;
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

//	public static Image loadImage(String imageName, ImageStyleClass style) {
//		try {
//			URL imageURL = DocumentHelper.class.getClassLoader().getResource(imageName);
//			Image image = Image.getInstance(imageURL);
//
//			image.setAlignment(style.getAlign().getValue());
//			if (style.getHeight() != null) {
//				image.scaleAbsoluteHeight(style.getHeight());
//			}
//			if (style.getWidth() != null) {
//				image.scaleAbsoluteWidth(style.getWidth());
//			}
//
//			return image;
//		} catch (BadElementException e) {
//			e.printStackTrace();
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

	public static Chunk createDefaultSeparator() {
		LineSeparator separator = new LineSeparator();
		separator.setLineWidth(0.1f);
		Chunk linebreak = new Chunk(separator);
		return linebreak;
	}
	//@formatter:oN
}
