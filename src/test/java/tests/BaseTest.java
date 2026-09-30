package tests;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import utils.ConfigReader;
import utils.ScreenshotListener;

@Listeners(ScreenshotListener.class)
public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    public Page getPage() {
        return page;
    }

    @BeforeMethod(alwaysRun = true)
    public void setup() {

       playwright = Playwright.create();
       playwright.selectors().setTestIdAttribute("data-qa");
       browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
       context = browser.newContext();
       context.setDefaultTimeout(ConfigReader.getTimeout());
       page = context.newPage();
       System.out.println("Browser started successfully");
    }

    @AfterMethod(alwaysRun = true)
    public void teardown(){
        if (page != null) {
            page.close();
        }
        if (context != null) {
            context.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null){
            playwright.close();
        }

        System.out.println("Browser closed successfully");
    }

}
