package br.jreport.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.DocumentException;

import br.jreport.core.JReportEngine;
import br.jreport.core.api.ReportOutputData;

/**
 * Unit test for simple App.
 */
public class AppTest {
	public static void main(String[] args) throws DocumentException, IOException {
		DemoReport demoReport = new DemoReport();
		ReportOutputData reportOutputData = JReportEngine.generate(demoReport, new PdfReport());
		FileOutputStream outputStream = new FileOutputStream(new File("target/text.pdf"));
		outputStream.write(reportOutputData.getOutputStream().toByteArray());
		outputStream.close();
	}
}
