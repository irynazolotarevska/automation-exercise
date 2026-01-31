package pages;

import com.microsoft.playwright.Page;

public class SignupLoginPage {

    private Page page;

    /* Login section */
    private String emailAddressLogin = "input[@data-qa='login-email']";
    private String password = "input[@placeholder='Password']";
    private String loginButton = "button[data-qa='login-button']";

    /* SignUp section */
    private String nameInput = "input[placeholder='Name']";
    private String emailAddressSignup = "input[data-qa='signup-email']";
    private String signupButton = "button[data-qa='signup-button']";

    public SignupLoginPage(Page page) { this.page = page; }

    public void enterName(String name){
        page.locator(nameInput).fill(name);
    }

    public void enterEmail(String email){
        page.locator(emailAddressSignup).fill(email);
    }

    public void clickSignupButton(){
        page.locator(signupButton).click();
    }

    public void signUp(String name, String email){
        page.locator(nameInput).fill(name);
        page.locator(emailAddressSignup).fill(email);
        page.locator(signupButton).click();
    }
}
