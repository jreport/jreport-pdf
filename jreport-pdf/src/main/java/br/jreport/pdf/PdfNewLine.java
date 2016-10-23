package br.jreport.pdf;

import java.util.Optional;

import com.lowagie.text.Chunk;

import br.jreport.core.api.NewLine;

public class PdfNewLine implements NewLine<Chunk> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PdfNewLine() {
		super();
	}

	@Override
	public Optional<Chunk> build() {
		return Optional.of(Chunk.NEWLINE);
	}

}
