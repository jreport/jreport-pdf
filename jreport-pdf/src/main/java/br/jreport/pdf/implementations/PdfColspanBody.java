package br.jreport.pdf.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.BlockElement;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import br.jreport.core.api.interfaces.NewColspanBody;
import br.jreport.core.api.property.NewTableProperty;
import br.jreport.core.impl.Style;
import br.jreport.pdf.helper.DocumentHelper;

public class PdfColspanBody implements NewColspanBody {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4829845217202185735L;

	private Integer headersColspan;

	private List<Cell> cells = new ArrayList<Cell>();

	public PdfColspanBody(Integer headersColspan) {
		this.headersColspan = headersColspan;
	}

	public NewColspanBody addText(String text) {
		Paragraph p = DocumentHelper.createText(text, Style.of(""));
		cells.add(getCell(p));
		return this;
	}

	public NewColspanBody addText(String text, Optional<Style> style) {
		Paragraph p = DocumentHelper.createText(text, style);
		cells.add(getCell(p));
		return this;
	}

	public NewColspanBody addImage(String src) {
		Image p = DocumentHelper.loadImage(src, Style.of(""));
		cells.add(getCell(p));
		return this;
	}

	public NewColspanBody addImage(String src, Optional<Style> style) {
		Image p = DocumentHelper.loadImage(src, style);
		cells.add(getCell(p));
		return this;
	}

	public <T> NewColspanBody addTable(NewTableProperty<T> tableAdapter) {
		Table table = DocumentHelper.createTable(tableAdapter, Style.of(""));
		cells.add(getCell(table));
		return this;
	}

	public <T> NewColspanBody addTable(NewTableProperty<T> tableAdapter, Optional<Style> style) {
		Table table = DocumentHelper.createTable(tableAdapter, style);
		cells.add(getCell(table));
		return this;
	}

	public NewColspanBody addEmptyCol() {
		Paragraph p = DocumentHelper.createText("", Style.of(""));
		cells.add(getCell(p));
		return this;
	}

	private Cell getCell(Paragraph paragraph) {
		if (paragraph == null) {
			Paragraph p = DocumentHelper.createText("Paragraph erro", Style.of(""));
			return getCell(p);
		}
		Cell cell = new Cell().add(paragraph);
		config(cell);
		return cell;
	}

	private Cell getCell(Image image) {
		if (image == null) {
			Paragraph p = DocumentHelper.createText("Image erro", Style.of(""));
			return getCell(p);
		}
		Cell cell = new Cell().add(image);
		config(cell);
		return cell;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Cell getCell(BlockElement blockElement) {
		if (blockElement == null) {
			Paragraph p = DocumentHelper.createText("Element erro", Style.of(""));
			return getCell(p);
		}
		Cell cell = new Cell().add(blockElement);
		config(cell);
		return cell;
	}

	private void config(Cell cell) {
		cell.setPadding(0);
		// cell.setTextAlignment(alignment);
		cell.setBorder(Border.NO_BORDER);
	}

	// private TextAlignment getAlign(String align) {
	// switch (align.trim().toLowerCase()) {
	// case "left":
	// return TextAlignment.LEFT;
	// case "right":
	// return TextAlignment.RIGHT;
	// case "center":
	// return TextAlignment.CENTER;
	// default:
	// return TextAlignment.LEFT;
	// }
	// }

	public NewColspanBody buildColspan() {
		return this;
	}

	public List<Cell> getCells() {
		return cells;
	}

	public void setCells(List<Cell> cells) {
		this.cells = cells;
	}

	public Integer getHeadersColspan() {
		return headersColspan;
	}

	public void setHeadersColspan(Integer headersColspan) {
		this.headersColspan = headersColspan;
	}

}
