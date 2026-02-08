package pages;

import com.microsoft.playwright.Page;

public class HomePage {

    private Page page;
    public static final String STARTURL = "https://automationexercise.com/";
    private String logo = "img[alt='Website for automation practice']";
    private String signupLoginButton = "a:has-text('Signup / Login')";
    private String loggedInText = "Logged in as ";
    private String deleteAccountButton = "Delete Account";

    public HomePage(Page page){
        this.page = page;
    }

    public void openHomePage() {
        page.navigate(STARTURL);

        /* google popup*/

        try {
            page.locator("p:has-text('Погоджуюся')").click();
        } catch (Exception e) {
            // Ignore if popup absent
        }
    }

    public boolean isLogoVisible(){
      return page.locator(logo).isVisible();
    }
    public void clickSignupLogin(){
        page.locator(signupLoginButton).click();
    }
    public boolean isLoggedInTextVisible(String username){
        return page.getByText(loggedInText + username).isVisible();
    }
    public void clickDeleteAccountButton(){
        page.locator(deleteAccountButton).click();
    }
}
