package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class AccountCreatedDeleted {
    private Page page;

    private String header = "h2[class='title text-center'] b";
    private String continueButton = ".btn.btn-primary";

    public AccountCreatedDeleted(Page page){
        this.page = page;
    }

    public boolean isHeaderVisible() {
      page.locator(header).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
       return page.locator(header).isVisible();
    }
    public void clickContinue(){
        page.locator(continueButton).click();
    }
}
