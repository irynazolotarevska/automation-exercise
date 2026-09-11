package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

public class HomePage  {

    private Page page;
    public static final String STARTURL = "https://automationexercise.com/";
    private String logo = "img[alt='Website for automation practice']";
    private Locator signupLoginButton;
    private String loggedInText = "Logged in as ";
    private String deleteAccountButton = "Delete Account";
    private String productsButton = "a[href='/products']";

    public HomePage(Page page){
        this.page = page;
        signupLoginButton = page.getByRole(AriaRole.LINK, new
                Page.GetByRoleOptions().setName("Signup / Login"));
    }

    public void openHomePage() {
        page.navigate(STARTURL);

        /* consent popup - not always present, so don't wait for it */
        Locator consentPopup = page.locator("p.fc-button-label:text-is('Consent')");
        if (consentPopup.count() > 0 && consentPopup.isVisible()) {
            consentPopup.click();
        }
    }

    public boolean isLogoVisible(){
        page.locator(logo).waitFor();
        return page.locator(logo).isVisible();
    }

    public void clickSignupLogin(){
        signupLoginButton.waitFor(new Locator.WaitForOptions().setTimeout(5000));
        signupLoginButton.click();
    }

    public boolean isLoggedInTextVisible(String username){
        page.getByText(loggedInText + username).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        return page.getByText(loggedInText + username).isVisible();
    }

    public void clickDeleteAccountButton(){
        page.getByText(deleteAccountButton).click();
    }

    public void clickProductButton(){
        page.locator(productsButton).click();
    }

}
