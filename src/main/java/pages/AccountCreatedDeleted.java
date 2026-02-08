package pages;

import com.microsoft.playwright.Page;

public class AccountCreatedDeleted {
    private Page page;

    private String header = "h2[class='title text-center'] b";
    private String continueButton = ".btn.btn-primary";

    public AccountCreatedDeleted(Page page){
        this.page = page;
    }

    public boolean isHeaderVisible() {
       return page.locator(header).isVisible();
    }
    public void clickContinue(){
        page.locator(continueButton).click();
    }
}
