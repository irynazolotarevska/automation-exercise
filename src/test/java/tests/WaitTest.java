package tests;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.testng.Assert;
import org.testng.annotations.Test;

import static pages.HomePage.STARTURL;

public class WaitTest extends BaseTest {

    @Test
    public void testWaitForLoadState(){
        page.navigate(STARTURL);
        page.waitForLoadState(LoadState.NETWORKIDLE);
        System.out.println("✓ Page fully loaded with NETWORKIDLE");
    }

    @Test
    public void testWaitForSelector(){
        page.navigate(STARTURL);
        page.waitForSelector("img[alt='Website for automation practice']");
        Assert.assertTrue(page.locator("img[alt='Website for automation practice']").isVisible());
        System.out.println("✓ Logo appeared and visible");
    }

    @Test
    public void testWaitForHidden() {
        page.navigate(STARTURL);

        try {
            page.locator("p:has-text('Погоджуюся')").click();
        } catch (Exception e) {}


        page.locator("p:has-text('Погоджуюся')").waitFor(
             new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN)
         );

        System.out.println("✓ Popup is hidden");
    }


}
