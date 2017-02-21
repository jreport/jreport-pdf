package br.jreport.pdf.implementations;

import java.util.ArrayList;
import java.util.List;

import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.BlockElement;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

import br.jreport.core.api.NewColspanBody;
import br.jreport.core.api.property.NewTableProperty;
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

	public NewColspanBody addText(String text, String align) {
		Paragraph p = DocumentHelper.createText(text, "");
		cells.add(getCell(p, getAlign(align)));
		return this;
	}

	public NewColspanBody addText(String text, String align, String style) {
		Paragraph p = DocumentHelper.createText(text, style);
		cells.add(getCell(p, getAlign(align)));
		return this;
	}

	public NewColspanBody addImage(String src, String align) {
		Image p = DocumentHelper.loadImage(src, "");
		cells.add(getCell(p, getAlign(align)));
		return this;
	}

	public NewColspanBody addImage(String src, String align, String style) {
		Image p = DocumentHelper.loadImage(src, style);
		cells.add(getCell(p, getAlign(align)));
		return this;
	}

	public <T> NewColspanBody addTable(NewTableProperty<T> tableAdapter, String align) {
		Table table = DocumentHelper.createTable(tableAdapter, "", "");
		cells.add(getCell(table, getAlign(align)));
		return this;
	}

	public <T> NewColspanBody addTable(NewTableProperty<T> tableAdapter, String align, String style, String headerClasse) {
		Table table = DocumentHelper.createTable(tableAdapter, style, headerClasse);
		cells.add(getCell(table, getAlign(align)));
		return this;
	}

	public NewColspanBody addEmptyCol() {
		Paragraph p = DocumentHelper.createText("", "");
		cells.add(getCell(p, getAlign("")));
		return this;
	}

	private Cell getCell(Paragraph paragraph, TextAlignment alignment) {
		if (paragraph == null) {
			Paragraph p = DocumentHelper.createText("Paragraph erro", "");
			return getCell(p, getAlign("center"));
		}
		Cell cell = new Cell().add(paragraph);
		config(alignment, cell);
		return cell;
	}

	private Cell getCell(Image image, TextAlignment alignment) {
		if (image == null) {
			Paragraph p = DocumentHelper.createText("Image erro", "");
			return getCell(p, getAlign("center"));
		}
		Cell cell = new Cell().add(image);
		config(alignment, cell);
		return cell;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Cell getCell(BlockElement blockElement, TextAlignment alignment) {
		if (blockElement == null) {
			Paragraph p = DocumentHelper.createText("Element erro", "");
			return getCell(p, getAlign("center"));
		}
		Cell cell = new Cell().add(blockElement);
		config(alignment, cell);
		return cell;
	}

	private void config(TextAlignment alignment, Cell cell) {
		cell.setPadding(0);
		cell.setTextAlignment(alignment);
		cell.setBorder(Border.NO_BORDER);
	}

	private TextAlignment getAlign(String align) {
		switch (align.trim().toLowerCase()) {
		case "left":
			return TextAlignment.LEFT;
		case "right":
			return TextAlignment.RIGHT;
		case "center":
			return TextAlignment.CENTER;
		default:
			return TextAlignment.LEFT;
		}
	}

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
