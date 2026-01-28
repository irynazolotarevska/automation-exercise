package tests;


import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest{

    private static final String STARTURL = "https://automationexercise.com/";

    @Test()
    public void verifyHomePageTitle(){

        page.navigate(STARTURL);
        String actualTitle = page.title();
        String expectedTitle = "Automation Exercise";
        Assert.assertEquals(actualTitle, expectedTitle);
        System.out.println("✓ Test passed!");
    }

    @Test
    public void verifyHomePageUrl(){
        page.navigate(STARTURL);
        Assert.assertTrue(page.url().contains(STARTURL));
        System.out.println("✓ Test passed!");
    }

    @Test
    public void verifyLogoIsVisible(){
        page.navigate(STARTURL);
        boolean isLogoVisible = page.locator("img[alt='Website for automation practice']").isVisible();
        Assert.assertTrue(isLogoVisible, "Logo should be visible!");
        System.out.println("✓ Test passed!");
    }

}
