package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AccountCreatedDeleted;
import pages.AccountInformationPage;
import pages.HomePage;
import pages.SignupLoginPage;

public class SignupTest extends BaseTest {
    HomePage homePage;
    SignupLoginPage signupPage;
    AccountInformationPage accountInformationPage;
    AccountCreatedDeleted accountCreated;
    AccountCreatedDeleted accountDeleted;

    @BeforeMethod(alwaysRun = true)
    public void setupPage(){
        homePage =new HomePage(page);
        signupPage = new SignupLoginPage(page);
        accountInformationPage = new AccountInformationPage(page);
        accountCreated = new AccountCreatedDeleted(page);
        accountDeleted = new AccountCreatedDeleted(page);
    }

    @Test(groups = {"smoke"})
    public void completeSignUpFlow(){
        homePage.openHomePage();
        homePage.clickSignupLogin();
        signupPage.signUp("AutoTest1","testforzoloto" + System.currentTimeMillis() +"@gmail.com");
        Assert.assertTrue(page.url().contains("/signup"));
        Assert.assertTrue(accountInformationPage.isAccountInformationHeaderVisible());
        accountInformationPage.enterAccountInformation("123456", "10", "July","1990");
        Assert.assertTrue(accountInformationPage.isAddressInformationHeader2Visible());
        accountInformationPage.enterAddressInformation("John", "Doe",
                "company name", "123 Test Avenue, Apt 4B","Address 2", "United States",
                "IL","Springfield", "62704","9999999999");
        Assert.assertTrue(accountCreated.isHeaderVisible());
        accountCreated.clickContinue();
        Assert.assertTrue(homePage.isLoggedInTextVisible("AutoTest1"));

        homePage.clickDeleteAccountButton();
        Assert.assertTrue(accountDeleted.isHeaderVisible());
        accountDeleted.clickContinue();
    }
    
}
