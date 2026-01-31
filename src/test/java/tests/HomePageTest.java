package tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignupLoginPage;

public class HomePageTest extends BaseTest{
    HomePage homePage;
    SignupLoginPage signupPage;

    @BeforeMethod
    public void setupPage(){
        homePage = new HomePage(page);
        signupPage = new SignupLoginPage(page);
    }

    @Test
    public void verifyHomePageTitle(){

        homePage.openHomePage();
        String actualTitle = page.title();
        String expectedTitle = "Automation Exercise";
        Assert.assertEquals(actualTitle, expectedTitle);
        System.out.println("✓ Test passed!");
    }

    @Test
    public void verifyHomePageUrl(){
        homePage.openHomePage();
        Assert.assertTrue(page.url().contains(HomePage.STARTURL));
        System.out.println("✓ Test passed!");
    }

    @Test
    public void verifyLogoIsVisible(){
        homePage.openHomePage() ;
        Assert.assertTrue(homePage.isLogoVisible(), "Logo should be visible!");
        System.out.println("✓ Test passed!");
    }

    @Test
    public void verifyUserCanNavigateToSignupPage(){
        homePage.openHomePage();
        homePage.clickSignupLogin();
        Assert.assertTrue(page.url().contains("/login"));
    }


}
