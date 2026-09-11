package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SignupLoginPage {

    private Page page;

    /* Login section */
    private String titleLoginToYourAccount = "Login to your account";
    private Locator emailAddressLogin;
    private String passwordLocator = "input[placeholder='Password']";
    private Locator loginButton;
    private String validationErrorLogInMessage = "Your email or password is incorrect!";

    /* SignUp section */
    private String nameInput = "input[placeholder='Name']";
    private Locator emailAddressSignup;
    private Locator signupButton;


    public SignupLoginPage(Page page) {
        this.page = page;
        emailAddressLogin = page.getByTestId("login-email");
        loginButton = page.getByTestId("login-button");
        emailAddressSignup = page.getByTestId("signup-email");
        signupButton = page.getByTestId("signup-button");
    }


    /* Login section */
    public boolean isTitleLoginToAcountVisible(){
        page.getByText(titleLoginToYourAccount).waitFor();
       return page.getByText(titleLoginToYourAccount).isVisible();
    }
    public void enterEmailForLogin(String email){
        emailAddressLogin.fill(email);
    }
    public void enterPassword(String password){
        page.locator(passwordLocator).fill(password);
    }
    public void clickLoginButton(){
        loginButton.click();
    }
    public boolean isErrorMessageVisible(){
       return page.getByText(validationErrorLogInMessage).isVisible();
    }

    /* SignUp Section */
    public void enterName(String name){
        page.locator(nameInput).fill(name);
    }
    public void enterEmail(String email){
        emailAddressSignup.fill(email);
    }
    public void clickSignupButton(){
        signupButton.click();
    }

    public void login(String email, String password) {
        enterEmailForLogin(email);
        enterPassword(password);
        clickLoginButton();
    }

    public void signUp(String name, String email){
        enterName(name);
        enterEmail(email);
        clickSignupButton();
    }


}
