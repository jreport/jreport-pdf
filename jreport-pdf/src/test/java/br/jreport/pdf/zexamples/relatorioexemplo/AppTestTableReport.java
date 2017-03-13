package br.jreport.pdf.zexamples.relatorioexemplo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import br.jreport.core.JReportEngine;
import br.jreport.core.api.aux.NewReportOutputData;
import br.jreport.pdf.PdfReport;

/**
 * Unit test for simple App.
 */
public class AppTestTableReport {

	public static void main(String[] args) throws IOException {
		DemoReportTable relatorioTitlerx = new DemoReportTable();
		NewReportOutputData reportOutputData = JReportEngine.generate(relatorioTitlerx, new PdfReport());
		FileOutputStream outputStream = new FileOutputStream(new File("target/RelatorioTable.pdf"));
		outputStream.write(reportOutputData.getOutputStream().toByteArray());
		outputStream.close();
	}
}
