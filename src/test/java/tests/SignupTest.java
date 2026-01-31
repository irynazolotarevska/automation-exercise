package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignupLoginPage;

public class SignupTest extends BaseTest {
    HomePage homePage;
    SignupLoginPage signupPage;

    @BeforeMethod
    public void setupPage(){
        homePage =new HomePage(page);
        signupPage = new SignupLoginPage(page);
    }

    @Test
    public void completeSignUpFlow(){
        homePage.openHomePage();
        homePage.clickSignupLogin();
        signupPage.signUp("AutoTest1","testforzoloto" + System.currentTimeMillis() +"@gmail.com");
        Assert.assertTrue(page.url().contains("/signup"));
    }

    @Test
    public void signupWithExistingEmail(){
        homePage.openHomePage();
        homePage.clickSignupLogin();
        signupPage.signUp("AutoTest1","testforzoloto@gmail.com");
        Assert.assertTrue(page.url().contains("/signup"));
    }
}
