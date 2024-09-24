package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import utils.BaseTest;
import utils.CommonUtils;

public class ListenersSFDC implements ITestListener{
	
	public void onTestFailure(ITestResult result) {
		BaseTest.test.get().addScreenCaptureFromPath(CommonUtils.captureScreenshot(BaseTest.getDriver()));
		BaseTest.test.get().log(Status.FAIL, result.getName()+ " Fail");
	}
	
	public void onTestSuccess(ITestResult result) {
		BaseTest.test.get().log(Status.PASS, result.getName()+ " Test Passed");
	}
	

}
