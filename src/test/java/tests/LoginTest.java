package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AccountCreatedDeleted;
import pages.HomePage;
import pages.SignupLoginPage;

public class LoginTest extends BaseTest {
    HomePage homePage;
    SignupLoginPage signupLoginPage;


@BeforeMethod
    public void loginPage(){
        homePage = new HomePage(page);
        signupLoginPage = new SignupLoginPage(page);
    }
@Test(invocationCount = 10)
    public void loginUserWithCorrectEmailAndPassword(){
        homePage.openHomePage();
        Assert.assertTrue(homePage.isLogoVisible());
        homePage.clickSignupLogin();
        Assert.assertTrue(signupLoginPage.isTitleLoginToAcoountVisible());
        signupLoginPage.login("testforzoloto@gmail.com","123456");
        Assert.assertTrue(homePage.isLoggedInTextVisible("AutoTest"));
    }

}
