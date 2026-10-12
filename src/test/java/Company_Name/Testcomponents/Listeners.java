package Company_Name.Testcomponents;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.IConfigurationListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Company_Name.pageobjects.Report_generation;

public class Listeners extends BaseTest implements ITestListener, IConfigurationListener {

	ExtentTest test;

	ExtentReports extent = Report_generation.report();

	ThreadLocal<ExtentTest> extenttest = new ThreadLocal<ExtentTest>();
	WebDriver driver;

	@Override
	public void onTestStart(ITestResult result) {

		test = extent.createTest(result.getMethod().getMethodName());

		extenttest.set(test);

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extenttest.get().log(Status.PASS, "Test passed....");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testcaseName = result.getMethod().getMethodName();
		ExtentTest failedTest = test != null ? test : extent.createTest(testcaseName);
		extenttest.get().fail(result.getThrowable());

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());

		} catch (Exception e1) {
			e1.printStackTrace();

		}
		String filepath=null;
		try {
			filepath = getscreenshort(testcaseName, driver);
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
		test.addScreenCaptureFromPath(filepath,testcaseName);
	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onConfigurationFailure(ITestResult result) {
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();

	}

}