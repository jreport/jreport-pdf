package br.jreport.pdf.aux;

import java.io.ByteArrayOutputStream;

import br.jreport.core.api.NewReportOutputData;

public class PdfReportOutputData implements NewReportOutputData {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ByteArrayOutputStream outputStream;

	public PdfReportOutputData(ByteArrayOutputStream outputStream) {
		super();
		this.outputStream = outputStream;
	}

	@Override
	public ByteArrayOutputStream getOutputStream() {
		return this.outputStream;
	}

	@Override
	public String getMimeType() {
		return "pdf";
	}

}
