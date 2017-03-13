package br.jreport.pdf.regions;

import java.util.Optional;
import java.util.function.BiConsumer;

import com.itextpdf.layout.Document;

import br.jreport.core.api.NewReport;
import br.jreport.core.api.aux.NewTableRow;
import br.jreport.core.api.datasource.NewDatasource;
import br.jreport.core.api.interfaces.NewColspanBody;
import br.jreport.core.api.property.NewTableProperty;
import br.jreport.core.api.regions.NewTitle;
import br.jreport.core.impl.Style;
import br.jreport.pdf.implementations.PdfColspanBody;
import br.jreport.pdf.implementations.PdfColspanLine;
import br.jreport.pdf.implementations.PdfImage;
import br.jreport.pdf.implementations.PdfNewLine;
import br.jreport.pdf.implementations.PdfNewPage;
import br.jreport.pdf.implementations.PdfNewSeparator;
import br.jreport.pdf.implementations.PdfText;

public class PdfRegionTitle implements NewTitle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Document document;

	private NewReport report;

	public PdfRegionTitle(Document document, NewReport report) {
		super();
		this.document = document;
		this.report = report;
	}

	@Override
	public NewTitle addText(String text) {
		PdfText.of(document, text).ifPresent(txt -> txt.build());
		return this;
	}

	@Override
	public NewTitle addText(String text, Optional<Style> styleClass) {
		PdfText.of(document, text, styleClass).ifPresent(txt -> txt.build());
		return this;
	}

	@Override
	public NewTitle addBrasao() {
		PdfImage.of(document, "brasao-republica.png", Style.of("image.brasao")).ifPresent(newImage -> newImage.build());
		return this;
	}

	@Override
	public NewTitle addImage(String src) {
		PdfImage.of(document, src).ifPresent(newImage -> newImage.build());
		return null;
	}

	@Override
	public NewTitle addImage(String src, Optional<Style> classe) {
		PdfImage.of(document, src, classe).ifPresent(newImage -> newImage.build());
		return this;
	}

	@Override
	public NewTitle addNewLine() {
		PdfNewLine.of(document).ifPresent(newLine -> newLine.build());
		return this;
	}

	@Override
	public NewTitle addSeparator() {
		PdfNewSeparator.of(document).ifPresent(newSeparator -> newSeparator.build());
		return this;
	}

	@Override
	public NewTitle addNewPage() {
		PdfNewPage.of(document).ifPresent(newPage -> newPage.build());
		return this;
	}

	@Override
	// TODO não gostei muito dessa sintaxe podemos verificar melhor maneira de
	// passar o document.
	public NewTitle addColspanline(NewColspanBody tableProperty) {
		PdfColspanLine.of(document, (PdfColspanBody) tableProperty).ifPresent(table -> table.build());
		;
		return this;
	}

	@Override
	public NewReport buildTitle() {
		return this.report;
	}

	@Override
	public <T, A extends NewTableProperty<T>> NewTitle addTable(A tableProperty) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T, A extends NewTableProperty<T>> NewTitle addTable(A tableProperty, Optional<Style> classe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T, A extends NewTableProperty<T>> NewTitle addTable(A tableProperty, BiConsumer<T, NewTableRow> eachRow) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T, A extends NewTableProperty<T>> NewTitle addTable(A tableProperty, BiConsumer<T, NewTableRow> eachRow,
			Optional<Style> classe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T, D extends NewDatasource<T>> NewTitle addList(D datasource) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T, D extends NewDatasource<T>> NewTitle addList(D datasource, Optional<Style> classe) {
		// TODO Auto-generated method stub
		return null;
	}

}
