package br.jreport.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import br.jreport.core.JReportEngine;
import br.jreport.core.api.NewReportOutputData;

/**
 * Unit test for simple App.
 */
public class AppTest {
	
	public static void main(String[] args) throws IOException {
		DemoReport demoReport = new DemoReport();
		NewReportOutputData reportOutputData = JReportEngine.generate(demoReport, new PdfReport());
		FileOutputStream outputStream = new FileOutputStream(new File("target/text2.pdf"));
		outputStream.write(reportOutputData.getOutputStream().toByteArray());
		outputStream.close();
	}
}
