package br.jreport.pdf;

import java.io.ByteArrayOutputStream;

import br.jreport.core.api.ReportOutputData;

public class PdfReportOutputData implements ReportOutputData {

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
