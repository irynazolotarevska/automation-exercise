package utils;

import com.microsoft.playwright.Page;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.BaseTest;

import java.nio.file.Paths;

public class ScreenshotListener implements ITestListener {


    @Override
    public void onTestFailure(ITestResult result) {
        BaseTest test = (BaseTest) result.getInstance();
        test.getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshots/" + result.getName() + System.currentTimeMillis() + ".png")));
    }
}
