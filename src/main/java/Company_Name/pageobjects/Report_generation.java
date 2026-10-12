package Company_Name.pageobjects;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Report_generation {
	
	
	public static ExtentReports report() {
		
		
		File reportDirectory = new File(System.getProperty("user.dir"), "report");
		reportDirectory.mkdirs();
		String file = new File(reportDirectory, "index.html").getAbsolutePath();
		
		ExtentSparkReporter report =new ExtentSparkReporter(file);
		
		report.config().setReportName("WebAutomation Result");
		report.config().setDocumentTitle("Test Result");
		
		
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Test", "Laxmi kanta");
		return extent;
	}

}
