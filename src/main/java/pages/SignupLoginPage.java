package pages;

import com.microsoft.playwright.Page;

public class SignupLoginPage {

    private Page page;

    /* Login section */
    private String titleLoginToYourAccount = "Login to your account";
    private String emailAddressLogin = "input[data-qa='login-email']";
    private String passwordLocator = "input[placeholder='Password']";
    private String loginButton = "button[data-qa='login-button']";

    /* SignUp section */
    private String nameInput = "input[placeholder='Name']";
    private String emailAddressSignup = "input[data-qa='signup-email']";
    private String signupButton = "button[data-qa='signup-button']";

    public SignupLoginPage(Page page) { this.page = page; }

    /* Login section */
    public boolean isTitleLoginToAcoountVisible(){
        page.getByText(titleLoginToYourAccount).waitFor();
       return page.getByText(titleLoginToYourAccount).isVisible();
    }
    public void enterEmailForLogin(String email){
        page.locator(emailAddressLogin).fill(email);
    }
    public void enterPassword(String password){
        page.locator(passwordLocator).fill(password);
    }
    public void clickLoginButton(){
        page.locator(loginButton).click();
    }

    /* SignUp Section */
    public void enterName(String name){
        page.locator(nameInput).fill(name);
    }
    public void enterEmail(String email){
        page.locator(emailAddressSignup).fill(email);
    }
    public void clickSignupButton(){
        page.locator(signupButton).click();
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
