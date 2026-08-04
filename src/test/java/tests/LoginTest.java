package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AccountCreatedDeleted;
import pages.HomePage;
import pages.SignupLoginPage;
import utils.ConfigReader;


public class LoginTest extends BaseTest {
    HomePage homePage;
    SignupLoginPage signupLoginPage;


    @BeforeMethod(alwaysRun = true)
    public void loginPage(){
        homePage = new HomePage(page);
        signupLoginPage = new SignupLoginPage(page);
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return new Object[][] {
                { "testforzoloto78@gmail.com", "123456" },
                { ConfigReader.getExistingUserEmail(), "1234567" }
        };
    }

    @Test(groups = {"smoke"})
    public void loginUserWithCorrectEmailAndPassword(){
        homePage.openHomePage();
        Assert.assertTrue(homePage.isLogoVisible());
        homePage.clickSignupLogin();
        Assert.assertTrue(signupLoginPage.isTitleLoginToAcoountVisible());
        signupLoginPage.login(ConfigReader.getExistingUserEmail(),"123456");
        Assert.assertTrue(homePage.isLoggedInTextVisible("AutoTest"));
    }

    @Test(dataProvider = "invalidLoginData",groups = {"regression"})
    public void testInvalidLogin(String email, String password) {
        homePage.openHomePage();
        homePage.clickSignupLogin();
        signupLoginPage.login(email, password);
        Assert.assertTrue(signupLoginPage.isErrorMessageVisible());
    }

}
